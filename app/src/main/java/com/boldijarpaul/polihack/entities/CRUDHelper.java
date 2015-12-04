package com.boldijarpaul.polihack.entities;

import java.util.List;

public class CRUDHelper {

	public static <T extends BaseObject> T addObject(T value, List<T> list) {
		value._id = (int) (Math.random() * 10000);
		list.add(value);
		return value;
	}

	public static <T extends BaseObject> T updateObject(T value, List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)._id == value._id) {
				list.set(i, value);
			}
		}
		return value;
	}

}
