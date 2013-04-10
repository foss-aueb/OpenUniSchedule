package com.foss.aueb.openunischedule.Tools;

import org.json.JSONArray;

public interface ScheduleDownloader {
	public JSONArray downloadSchedule();
	public boolean checkForExistingUpdate();
}
