package Programmers;
import java.util.*;
public class pg_42627_디스크컨트롤러 {
    class Solution {
        public int solution(int[][] jobs) {
            PriorityQueue<WaitTask> wait = new PriorityQueue<>();
            PriorityQueue<RunTask> run = new PriorityQueue<>();
            for(int i = 0; i<jobs.length;  i++){
                int start = jobs[i][0];
                int time = jobs[i][1];
                wait.add(new WaitTask(start,new RunTask(i,start,time)));
            }
            int now = 0;
            int sum = 0;
            run.add(wait.poll().runTask);
            now = run.peek().start;
            while(!wait.isEmpty()){
                if(wait.peek().start<=now){
                    run.add(wait.poll().runTask);
                }
                else if(run.isEmpty()){
                    run.add(wait.poll().runTask);
                    now = run.peek().start;
                }
                else{
                    RunTask rt = run.poll();
                    now += rt.runtime;
                    sum += now - rt.start;
                }
            }
            while(!run.isEmpty()){
                RunTask rt = run.poll();
                now += rt.runtime;
                sum += now - rt.start;
            }
            return sum/jobs.length;
        }
        static class RunTask implements Comparable<RunTask>{
            int number; int start; int runtime;
            RunTask(int number, int start, int runtime){
                this.number = number;
                this.start = start;
                this.runtime = runtime;
            }
            @Override
            public int compareTo(RunTask o){
                if(this.runtime != o.runtime)return Integer.compare(this.runtime,o.runtime);
                if(this.start != o.start)return Integer.compare(this.start,o.start);
                return Integer.compare(this.number,o.number);
            }
        }
        static class WaitTask implements Comparable<WaitTask>{
            int start;
            RunTask runTask;
            WaitTask(int start,RunTask runTask){
                this.start = start;
                this.runTask = runTask;
            }
            @Override
            public int compareTo(WaitTask o){
                return Integer.compare(this.start,o.start);
            }
        }
        
    }
}
