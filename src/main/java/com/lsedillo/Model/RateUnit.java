package com.lsedillo.Model;

public class RateUnit {
   DataUnit data;
   TimeUnit time;
   RateUnit(String data, String time) {
      data = chooseData(data);
      time = chooseTime(time);
   }

   private chooseData(String data) {

   }
}
