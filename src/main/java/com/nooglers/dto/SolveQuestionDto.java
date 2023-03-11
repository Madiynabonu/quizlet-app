package com.nooglers.dto;

import com.nooglers.domains.test.Variant;

import java.util.List;

public record SolveQuestionDto( String quizType , String definition , List<Variant> variants , int id,int totalQuestionCount,int currentQuestionCount ) {
}
