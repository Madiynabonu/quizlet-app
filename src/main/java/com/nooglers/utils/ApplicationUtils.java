package com.nooglers.utils;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ApplicationUtils {

    public static final String DELIM = "/\\*/\\*";
    public static Random random = new Random();
    public static final int MAX_QUESTION_COUNT = 7;
    public static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("##");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");




}
