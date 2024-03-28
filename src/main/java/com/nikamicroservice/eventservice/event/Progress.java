package com.nikamicroservice.eventservice.event;

import java.util.List;

public record Progress(List<DailyProgress> dailyProgressList) {
}
