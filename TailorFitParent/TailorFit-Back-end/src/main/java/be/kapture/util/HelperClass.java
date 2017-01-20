package be.kapture.util;

import be.kapture.enums.Gender;

public class HelperClass {
	public static String isStringEmpty(String string){
		if(string != null  && !string.trim().isEmpty()){
			return string;
		}
		throw new IllegalArgumentException();
	}
	public static String isStringEmpty(String string, String error){
		if(string != null  && !string.trim().isEmpty()){
			return string;
		}
		throw new IllegalArgumentException(error);
	}
	public static int isIntStricltyPositive(int number){
		if(number > 0){
			return number;
		}
		throw new IllegalArgumentException();
	}
	public static int isIntStricltyPositive(int number, String error){
		if(number > 0){
			return number;
		}
		throw new IllegalArgumentException(error);
	}
	public static Gender isEnumNotEmpty(Gender gender) {
		if(gender != null){
			return gender;
		}
		throw new IllegalArgumentException();
	}
	public static Gender isEnumNotEmpty(Gender gender, String error) {
		if(gender != null){
			return gender;
		}
		throw new IllegalArgumentException(error);
	}
}
