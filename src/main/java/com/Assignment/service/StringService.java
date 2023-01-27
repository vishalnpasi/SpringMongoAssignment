package com.Assignment.service;

import com.Assignment.models.StringModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StringService {
    public String getVowels(StringModel sm){
        Map<Character,Integer> map = new HashMap<>();
        String str = sm.getStr();
        char []arr=str.toCharArray();
        int vowels =0, specialChar=0;
        String res = "";
        for(int i = 0;i<arr.length;i++){
            if(arr[i]=='a' || arr[i]=='e' || arr[i]=='i' || arr[i]=='u' || arr[i]=='o'){
                vowels++;
            }
            else{
//              int ascii = (int)arr[i];
                if(arr[i]<48 || (arr[i]>57 && arr[i]<65) || (arr[i]>90 && arr[i]<97) || arr[i]>122)
                    specialChar++;
            }
        }
        res+="\nTotal Vowels : "+vowels+"\nTotal Special Char: "+specialChar;
        return res;
//        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}

