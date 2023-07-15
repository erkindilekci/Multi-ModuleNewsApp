package com.erkindilekci.multimodulenewsapp.navigation

import com.erkindilekci.common_utils.Activities
import com.erkindilekci.common_utils.Navigator
import com.erkindilekci.presentation.GoToNewsActivity
import com.erkindilekci.search_presentation.GoToSearchActivity

class DefaultNavigator : Navigator.Provider {

    override fun getActivities(activities: Activities): Navigator {
        return when (activities) {
            Activities.NewsActivity -> {
                GoToNewsActivity
            }

            Activities.SearchActivity -> {
                GoToSearchActivity
            }
        }
    }
}
