package com.ebikram.webservice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class RestApi {
	
	@GetMapping("/isPyramid/{word}")
	public static ResponseEntity<Boolean> isPyramid(@PathVariable("word")String str){ // get "word" from the url
		//create a result variable
    	boolean flag = true;
    	Map<Character, Integer> map = new HashMap<>();
    	// create a frequency map for all the chars
    	for(int i = 0; i<str.length(); i++){
    		if(!map.containsKey(str.charAt(i)) ){
    			map.put(str.charAt(i), 1);
    		}else{
    			map.put(str.charAt(i), map.get(str.charAt(i))+1);
    		}
    	}
    	// put values in a list so that it can be sorted to verify pyramid 
    	List<Integer> list = map.values().stream().collect(Collectors.toList());
    	//sort the list
    	Collections.sort(list);
    	// check the list if sorted elements are consecutive 
    	for(int i =0; i<list.size()-1; i++){
    		System.out.println(list.get(i)+" : "+ list.get(i+1));
    		if(list.get(i) != list.get(i+1)-1){
    			flag = false;
    			break;
    		}
    	}
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
    	
    }

}
