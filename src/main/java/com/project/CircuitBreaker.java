package com.project;

// 패턴: 서킷브레이커 (Circuit Breaker)
// 외부 서비스 호출이 계속 실패할 때, 요청을 차단해서 장애 전파를 막는 패턴.
//
// 상태 3가지:
// - CLOSED   : 정상. 요청 통과. 실패 횟수 누적.
// - OPEN     : 차단. 요청 즉시 실패 반환. 일정 시간 후 HALF_OPEN으로 전환.
// - HALF_OPEN: 탐색. 요청 1개 통과시켜 성공하면 CLOSED, 실패하면 다시 OPEN.

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class CircuitBreaker {

    enum State {CLOSED, HALF_OPEN, OPEN}

    private State state = State.CLOSED;

    private Queue<Boolean> queue = new LinkedList();

    private LocalDateTime openTime;

    public void execute(boolean isSuccess){

        System.out.println("상태 :" + state);

        switch(state){
            case CLOSED: {
                addQueue(isSuccess);
                openCheck();
                break;
            }

            case OPEN: {
                if (Duration.between(openTime, LocalDateTime.now()).getSeconds() >= 10) {
                    state = State.HALF_OPEN;
                }
                break;
            }

            case HALF_OPEN: {
                if(isSuccess){
                    state = State.CLOSED;
                }else{
                    state = State.OPEN;
                }
                break;
            }
        }
    }

    private void addQueue(boolean isSuccess) {
        if(queue.size() >= 10){
            queue.poll();
        }
        queue.add(isSuccess);
    }

    public void openCheck(){

        if(queue.size() >= 5) {
            int failCnt = 0;
            for (boolean isSuccess : queue) {
                if (!isSuccess) failCnt++;
            }

            double failRate = failCnt / queue.size();

            if (failRate > 0.5) state = State.HALF_OPEN;

            openTime = LocalDateTime.now();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        CircuitBreaker circuitBreaker = new CircuitBreaker();

        circuitBreaker.execute(false);
        circuitBreaker.execute(false);
        circuitBreaker.execute(true);
        circuitBreaker.execute(false);
        circuitBreaker.execute(true);

    }
}
