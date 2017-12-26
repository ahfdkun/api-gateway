package com.ahfdkun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRoute {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("(?<name>^.+)-(?<version>v.+$)");
		Matcher matcher = pattern.matcher("userservice-v1");
		if (matcher.matches()) {
			System.out.println(matcher.group("name"));
			System.out.println(matcher.group("version"));
		}
	}
}
