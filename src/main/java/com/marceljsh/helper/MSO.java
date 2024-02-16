// **************************************************************************
// * This code was created by Marcel Joshua (https://github.com/marceljsh)
// * within the context of One Piece Spring REST API.
// * Copyright (©) 2024 by Marcel Joshua, all rights reserved.
// *
// * This file was written using Java Spring Boot
// * and follows the principles of SOLID.
// *
// * Feel free to use or modify this code for your own purposes,
// * but please include this copyright notice.
// **************************************************************************

package com.marceljsh.helper;

import java.util.HashMap;
import java.util.Map;

public class MSO {

	public static Map<String, Object> assemble(Object... keyValuePairs) {
		if (keyValuePairs.length % 2 != 0) {
			throw new IllegalArgumentException("some key or value is missing");
		}

		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < keyValuePairs.length; i += 2) {
			if (!(keyValuePairs[i] instanceof String)) {
				throw new IllegalArgumentException("key must be a string");
			}
			map.put((String) keyValuePairs[i], keyValuePairs[i + 1]);
		}

		return map;
	}
}
