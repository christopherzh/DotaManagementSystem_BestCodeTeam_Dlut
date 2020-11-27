package util;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int h, int m, int s){
        hour = h;
        minute = m;
        second = s;
        adjustTime();
    }
    private void adjustTime(){
        int carrySec = second/60;
        second = second% 60;
        int carryMin = (minute+carrySec)/60;
        minute = (minute+carrySec)%60;
        hour += carryMin;
    }
    public void addTime(Time time){
        this.hour += time.hour;
        this.minute += time.minute;
        this.second += time.second;
        adjustTime();
    }
    public Time  deltaTime(Time endTime){
        // this is the start time
        int sec = endTime.second-second;
        int cSec =0;
        while(sec<0){
            cSec++;
            sec+=60;
        }
        int min = endTime.minute-minute-cSec;
        int cMin = 0;
        while(min<0){
            cMin++;
            min+=60;
        }
        int h = endTime.hour-hour-cMin;
        Time rtnTime = new Time(h,min,sec);
        return rtnTime;
    }

    @Override
    public String toString() {
        return
                hour +
                ":" + minute +
                ":" + second ;
    }
}
