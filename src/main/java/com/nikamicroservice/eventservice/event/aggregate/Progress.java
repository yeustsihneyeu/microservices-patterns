package com.nikamicroservice.eventservice.event.aggregate;

import java.util.List;

public record Progress(List<DailyProgress> dailyProgressList) {
}
