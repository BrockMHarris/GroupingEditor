package ProgPack;


public class Timer{

    long beginTime;
    long endTime;
    long startPauseTime;
    long StoppedTime;
    long startTime;

    public Timer(){
        startTime = System.currentTimeMillis();
    }

    public void begin(){
        beginTime = System.currentTimeMillis();
    }

    public void end(){
        endTime = System.currentTimeMillis();
    }

    public void pause(){
        startPauseTime = System.currentTimeMillis();
    }

    public void unPause(){
        float unPauseTime = System.currentTimeMillis();
        StoppedTime += (unPauseTime - startPauseTime);
    }

    public long getFinalTime(){
        return endTime - (beginTime + StoppedTime);
    }

    public long getCurrentTime(){
        return System.currentTimeMillis() - (startTime + StoppedTime);
    }
}
