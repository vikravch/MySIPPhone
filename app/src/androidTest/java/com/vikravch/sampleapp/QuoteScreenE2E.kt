package com.vikravch.sampleapp

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule

import androidx.navigation.NavHostController
import com.vikravch.sampleapp.core.NetworkInfo
import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository
import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.GetQuote
import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.QuoteUseCases
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.AddUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.DeleteUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.GetAllUsers
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.GetUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.UpdateUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.UsersUseCases
import com.vikravch.sampleapp.simple_feature.presentation.page.data_page.DataPageViewModel
import com.vikravch.sampleapp.simple_feature.presentation.page.quote_page.QuotePage
import com.vikravch.sampleapp.simple_feature.presentation.page.quote_page.QuotePageViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.vikravch.sampleapp.simple_feature.data.fake.QuoteFakeRepository
import com.vikravch.sampleapp.simple_feature.data.fake.UserFakeRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Before

@HiltAndroidTest
class QuoteScreenE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    private lateinit var navController: NavHostController
    private lateinit var networkInfo: NetworkInfo
    private lateinit var quoteRepository: QuoteRepository
    private lateinit var userRepository: UserRepository
    private lateinit var quoteUseCases: QuoteUseCases
    private lateinit var userUseCases: UsersUseCases
    private lateinit var quoteViewModel: QuotePageViewModel
    private lateinit var dataPageViewModel: DataPageViewModel

    @Before
    fun setup() {
        //every{ networkInfo.isConnected() } returns true
        quoteRepository = QuoteFakeRepository()
        userRepository = UserFakeRepository()

        quoteUseCases = QuoteUseCases(getQuote = GetQuote(quoteRepository))
        userUseCases = UsersUseCases(
            getUser = GetUser(userRepository),
            getAllUsers = GetAllUsers(userRepository),
            addUser = AddUser(userRepository),
            updateUser = UpdateUser(userRepository),
            deleteUser = DeleteUser(userRepository)
        )
        quoteViewModel = QuotePageViewModel(quoteUseCases)
        dataPageViewModel = DataPageViewModel(userUseCases)
    }

    @Test
    fun activityShouldStartFromLoginScreen() {
        //networkInfo = mockk(relaxed = true)
        //every { networkInfo.isConnected() } returns true
        composeRule.activity.setContent {
            MainActivityUI(
                quotePageViewModel = quoteViewModel,
                dataPageViewModel = dataPageViewModel
            )
        }
        composeRule.onNodeWithText("Add User").assertExists()
    }

}