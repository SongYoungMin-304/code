package com.project;

// 패턴: 서킷브레이커 (Circuit Breaker)
// 외부 서비스 호출이 계속 실패할 때, 요청을 차단해서 장애 전파를 막는 패턴.
//
// 상태 3가지:
// - CLOSED   : 정상. 요청 통과. 실패 횟수 누적.
// - OPEN     : 차단. 요청 즉시 실패 반환. 일정 시간 후 HALF_OPEN으로 전환.
// - HALF_OPEN: 탐색. 요청 1개 통과시켜 성공하면 CLOSED, 실패하면 다시 OPEN.

import java.util.function.Supplier;

public class CircuitBreaker {

    public enum State { CLOSED, OPEN, HALF_OPEN }

    private State state = State.CLOSED;
    private int failureCount = 0;

    private final int failureThreshold;  // 몇 번 실패하면 OPEN
    private final long retryTimeoutMs;   // OPEN 상태 유지 시간 (ms)
    private long openedAt;               // OPEN 된 시각

    public CircuitBreaker(int failureThreshold, long retryTimeoutMs) {
        this.failureThreshold = failureThreshold;
        this.retryTimeoutMs = retryTimeoutMs;
    }

    public <T> T call(Supplier<T> action) {
        switch (state) {
            case OPEN:
                if (System.currentTimeMillis() - openedAt >= retryTimeoutMs) {
                    state = State.HALF_OPEN; // 시간이 지나면 HALF_OPEN으로 전환
                } else {
                    throw new RuntimeException("[OPEN] 서킷 차단 중 — 요청 거부");
                }
                // HALF_OPEN으로 넘어가서 아래 실행
            case HALF_OPEN:
            case CLOSED:
                try {
                    T result = action.get();
                    onSuccess();
                    return result;
                } catch (Exception e) {
                    onFailure();
                    throw e;
                }
            default:
                throw new IllegalStateException("알 수 없는 상태");
        }
    }

    private void onSuccess() {
        failureCount = 0;
        state = State.CLOSED;
        System.out.println("[SUCCESS] 상태: " + state);
    }

    private void onFailure() {
        failureCount++;
        System.out.println("[FAILURE] 실패 횟수: " + failureCount);

        if (state == State.HALF_OPEN || failureCount >= failureThreshold) {
            state = State.OPEN;
            openedAt = System.currentTimeMillis();
            System.out.println("[OPEN] 서킷 열림 — " + retryTimeoutMs + "ms 동안 차단");
        }
    }

    public State getState() { return state; }

    public static void main(String[] args) throws InterruptedException {
        // 실패 2번이면 OPEN, 1초 후 HALF_OPEN
        CircuitBreaker cb = new CircuitBreaker(2, 1000);

        Supplier<String> unstableService = () -> {
            if (Math.random() < 0.7) throw new RuntimeException("서비스 오류");
            return "응답 성공";
        };

        // 1. CLOSED → 실패 누적 → OPEN
        System.out.println("=== 요청 시작 ===");
        for (int i = 0; i < 5; i++) {
            try {
                String result = cb.call(() -> "항상 실패" + (1/0)); // 강제 실패
            } catch (Exception e) {
                System.out.println("예외: " + e.getMessage() + " | 상태: " + cb.getState());
            }
        }

        // 2. OPEN 상태에서 즉시 차단
        System.out.println("\n=== OPEN 상태에서 요청 ===");
        try {
            cb.call(() -> "차단되어야 함");
        } catch (Exception e) {
            System.out.println("예외: " + e.getMessage());
        }

        // 3. 1초 대기 후 HALF_OPEN → 성공하면 CLOSED
        System.out.println("\n=== 1초 대기 후 HALF_OPEN ===");
        Thread.sleep(1100);
        try {
            String result = cb.call(() -> "복구 성공!");
            System.out.println("결과: " + result + " | 상태: " + cb.getState());
        } catch (Exception e) {
            System.out.println("예외: " + e.getMessage() + " | 상태: " + cb.getState());
        }
    }
}
