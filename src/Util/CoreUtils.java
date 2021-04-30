package Util;

import java.util.Random;

public class CoreUtils {
	public static int randInt(int min, int max) {

		Random rand = new Random(4020);

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
