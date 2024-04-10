package com.example.finalproject

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.theme.FinalProjectTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                    color = Color(0xfffff9e5)
                ) {
                    StartScreen()
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(visible) {
        // Wait 3 seconds and then show the logo
        delay(3000)
        // Update the visibility to false after the delay
        visible = false
    }

    Box(
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            Image(
                painter = painterResource(R.drawable.mindful_memo_logo),
                contentDescription = "The logo for Mindful Memo App which has a green " +
                        "background with a heart symbol in a thought bubble symbol",
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .size(300.dp)
                    .padding(10.dp)
            )
        }
    }

    // When logo fades out, then we will display the navigation bar
    if (!visible) {
        NavigationBar()
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var selectedScreen by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = Color(0xfffff9e5),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.mindful_memo_name),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displaySmall,
                        //fontFamily = balooFamily,
                        modifier = modifier
                            .padding(start = (screenWidth / 5))

                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(R.color.seed),
                    titleContentColor = colorResource(R.color.md_theme_dark_onSecondary)
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (selectedScreen) {
                    0 -> HomeScreen()
                    1 -> JournalScreen()
                    2 -> CalendarScreen()
                    3 -> SettingsScreen()
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(R.color.seed),
                contentColor = colorResource(R.color.md_theme_dark_onSecondary),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(50.dp),
                    modifier = modifier
                        .padding(start = (screenWidth / 12))
                ) {
                    BottomBarNavigationIcon(
                        painterId = painterResource(R.drawable.home_fill0_wght400_grad0_opsz24),
                        contentDescription = stringResource(R.string.home_icon_content_description),
                        onClick = { selectedScreen = 0 },
                        modifier = modifier
                    )

                    BottomBarNavigationIcon(
                        painterId = painterResource(R.drawable.book_2_fill0_wght400_grad0_opsz24),
                        contentDescription = stringResource(R.string.book_icon_content_description),
                        onClick = { selectedScreen = 1 },
                        modifier = modifier
                    )

                    BottomBarNavigationIcon(
                        painterId = painterResource(R.drawable.calendar_month_fill0_wght400_grad0_opsz24),
                        contentDescription = stringResource(R.string.calendar_icon_content_description),
                        onClick = { selectedScreen = 2 },
                        modifier = modifier
                    )

                    BottomBarNavigationIcon(
                        painterId = painterResource(R.drawable.settings_fill0_wght400_grad0_opsz24),
                        contentDescription = stringResource(R.string.settings_icon_content_description),
                        onClick = { selectedScreen = 3 },
                        modifier = modifier
                    )
                }
            }
        }
    )
}

@Composable
fun BottomBarNavigationIcon(
    painterId: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterId,
            tint = colorResource(R.color.md_theme_light_onPrimaryContainer),
            contentDescription = contentDescription,
            modifier = modifier
                .size(35.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    var randomNumber by remember { mutableStateOf(Random.nextInt(1, 8)) }
    var wordAffirmation by remember { mutableStateOf("")}
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        //verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
    ) {
        // month header
        Text(
            text = selectedDate.month.toString() + " "+ selectedDate.dayOfMonth.toString() + ", "+ selectedDate.year.toString(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    }

    if (randomNumber == 1) {
        wordAffirmation = "I Believe In Myself!"
    } else if (randomNumber == 2) {
        wordAffirmation = "Don't Forget To Smile!"
    } else if (randomNumber == 3) {
        wordAffirmation = "You Can do it!"
    } else if (randomNumber == 4) {
        wordAffirmation = "Work Hard and Sleep Well"
    } else if (randomNumber == 5) {
        wordAffirmation = "Everything Is Going to Be OK"
    } else if (randomNumber == 6) {
        wordAffirmation = "You Will have a Great Day"
    } else {
        wordAffirmation = "Better Things are Coming"
    }

    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "Read This Message:",
        fontSize = 15.sp
    )
    Text(
        text = wordAffirmation,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.Cursive,
        fontSize = 30.sp,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalScreen(
    modifier: Modifier = Modifier
) {
    Text(
        text = "How are you feeling today?",
        fontWeight = FontWeight.SemiBold,
        fontSize = 25.sp,
        letterSpacing = 2.sp,
        modifier = modifier
            .padding(bottom = 50.dp)
    )

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TextBoxJournalEntry()

        Spacer(modifier = modifier.padding(20.dp))
        MoodTracker()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBoxJournalEntry(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val infoStore = EntryDataStore(context)

    var entryTokenValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val nameTokenText = infoStore.getJournalEntry.collectAsState(initial = "")

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Text(
        text = "Today's Entry:",
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 2.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(bottom = 20.dp)

    )
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .padding(5.dp)
    ) {
        Text(
            text = nameTokenText.value,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.md_theme_light_primary),
            fontSize = 20.sp
        )
    }

    Spacer(modifier = Modifier.height(10.dp))


    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(start = screenWidth / 10)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = entryTokenValue.value,
                onValueChange = { entryTokenValue.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colorResource(R.color.md_theme_light_onPrimaryContainer),
                    cursorColor = colorResource(R.color.md_theme_light_onPrimaryContainer),
                    focusedIndicatorColor = colorResource(R.color.seed),
                    unfocusedIndicatorColor = Color(0xffb8e0f6),
                    containerColor = Color.White
                ),
            )

        }

    }

    Spacer(modifier = Modifier.height(15.dp))

    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                infoStore.saveEntry(entryTokenValue.value.text)
            }
        },
        colors = ButtonDefaults.buttonColors(colorResource(R.color.md_theme_light_tertiary)),
        modifier = modifier
            .padding(start = screenWidth / 3)
    ) {
        Text(
            text = "Update"
        )
    }

}

data class EmojiDataClass(
    val emoji: Painter,
    val contentDescription: String
)

@Composable
fun MoodTracker(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var emojiSelection by remember { mutableStateOf<EmojiDataClass?>(null) }

    // List of emoji
    val emojiList = listOf(
        EmojiDataClass(
            painterResource(R.drawable.sentiment_satisfied_fill0_wght400_grad0_opsz24),
            stringResource(R.string.satisfied_emoji_content_description)
        ),
        EmojiDataClass(
            painterResource(R.drawable.sentiment_calm_fill0_wght400_grad0_opsz24),
            stringResource(R.string.calm_emoji_content_description)
        ),
        EmojiDataClass(
            painterResource(R.drawable.sentiment_neutral_fill0_wght400_grad0_opsz24),
            stringResource(R.string.neutral_emoji_content_description)
        ),
        EmojiDataClass(
            painterResource(R.drawable.sentiment_dissatisfied_fill0_wght400_grad0_opsz24),
            stringResource(R.string.dissatisfied_emoji_content_description)
        ),
        EmojiDataClass(
            painterResource(R.drawable.sentiment_stressed_fill0_wght400_grad0_opsz24),
            stringResource(R.string.stressed_emoji_content_description)
        ),
        EmojiDataClass(
            painterResource(R.drawable.sentiment_worried_fill0_wght400_grad0_opsz24),
            stringResource(R.string.worried_emoji_content_description)
        ),
    )

    Surface(
        shape = RoundedCornerShape(20.dp),
    ) {
        Box(
            modifier = modifier
                .background(Color(0xffc0e1b0))
        ) {
            // Display two rows of emojis
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    // Get the emojis to align to the middle
                    .padding(start = (screenWidth / 8))
            ) {
                RowOfEmojis(emojiList.take(3), emojiSelection) { emojiSelection = it }
                RowOfEmojis(emojiList.drop(3), emojiSelection) { emojiSelection = it }
            }

        }
    }

}

@Composable
fun RowOfEmojis(
    emojiList: List<EmojiDataClass>,
    emojiSelection: EmojiDataClass?,
    currentEmoji: (EmojiDataClass) -> Unit,
) {

    var emojiTint by remember {
        mutableStateOf(false)
    }

    // Display a row of emojis
    Row(
        // Space between emojis
        horizontalArrangement = Arrangement.spacedBy(50.dp),
        modifier = Modifier
            .fillMaxWidth()
            // Padding between the rows
            .padding(top = 10.dp)
            .padding(bottom = 10.dp)
    ) {
        emojiList.forEach { emojiDataClass ->
            IconButton(
                onClick = { currentEmoji(emojiDataClass); emojiTint = true },
                modifier = Modifier
                    .background(
                        shape = CircleShape,
                        color = if (emojiSelection == emojiDataClass) {
                            //colorResource(R.color.md_theme_light_surfaceVariant)
                            Color.White
                        } else {
                            Color.Transparent
                        }
                    )
            ) {
                EmojiItem(
                    emojiDataClass
                )
            }
        }
    }
}

@Composable
fun EmojiItem(
    singleEmoji: EmojiDataClass,
    modifier: Modifier = Modifier
) {
    // Load the emoji image using painterResource
    val emojiImage: Painter = singleEmoji.emoji
    val emojiDescription: String = singleEmoji.contentDescription

    Image(
        painter = emojiImage,
        contentDescription = emojiDescription,
        colorFilter = ColorFilter.tint(colorResource(R.color.md_theme_light_onPrimaryContainer)),
        modifier = modifier
            // Emoji image size
            .size(60.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    val arrowIconSize = 30.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        // Adding space between the header and today button
        Spacer(modifier = modifier.height(60.dp))

        // Button for Today
        Button(
            onClick = { selectedDate = LocalDate.now() },
            colors = ButtonDefaults.buttonColors(colorResource(R.color.md_theme_light_tertiary)),
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(R.drawable.today_fill0_wght400_grad0_opsz24),
                contentDescription = "To go to today's date",
                modifier = modifier
                    .size(25.dp)
            )
            // Spacing between icon and text
            Spacer(modifier = modifier.width(4.dp))

            Text("Today")
        }

        // Adding space between the today button and calendar component
        Spacer(modifier = modifier.height(30.dp))

        // Add in which Month this is
        //THIS IS THE ONE THAT WORKS
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {

            // Backward arrow button
            IconButton(
                onClick = {
                    selectedDate = selectedDate.minusMonths(1)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_fill0_wght400_grad0_opsz24),
                    contentDescription = "Arrow back",
                    modifier = modifier
                        .size(arrowIconSize)
                )
            }

            // Month header
            Text(
                text = selectedDate.month.toString(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            // Year header
            Text(
                text = selectedDate.year.toString(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            // Forward arrow button
            IconButton(
                onClick = {
                    selectedDate = selectedDate.plusMonths(1)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_forward_fill0_wght400_grad0_opsz24),
                    contentDescription = "Arrow forward",
                    modifier = modifier
                        .size(arrowIconSize)
                )
            }
        }

        // Adding space between the header and today button
        Spacer(modifier = modifier.height(10.dp))

        // Calendar
        Surface(
            shape = RoundedCornerShape(10.dp),
        ) {
            Box(
                modifier = modifier
                    .background(Color(0xffc0e1b0))
            ) {
                Calendar(daysInMonthList = generateDaysInMonth(selectedDate), onDayClick = { day ->
                    selectedDate = selectedDate.withDayOfMonth(day)
                })
            }
        }

    }
}

// Data class for us to be able to get the date and keep track of date selection
data class CalendarDay(
    val dayOfMonth: Int,
    val isSelected: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    daysInMonthList: List<CalendarDay>,
    onDayClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    //Text("Calendar Screen", fontWeight = FontWeight.Bold)
    LazyColumn {
        // Weekdays header
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(25.dp)
                    .background(colorResource(R.color.md_theme_light_tertiary))

            ) {
                // Styling for days of the week Mon-Sun
                DayOfWeek.values().forEach { dayOfWeek ->
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        //color = colorResource(R.color.md_theme_light_primaryContainer),
                        color = Color.White,
                        modifier = modifier
                            .weight(1f)
                    )
                }
            }
        }

        // Calendar days
        items(daysInMonthList.chunked(7)) { week ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    // Padding between rows
                    .padding(10.dp)
            ) {
                week.forEach { day ->
                    DayItem(day = day, onDayClick = onDayClick)
                }
            }
        }
    }
}

@Composable
fun DayItem(
    day: CalendarDay,
    onDayClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable {
                if (day.dayOfMonth != 0) {
                    onDayClick(day.dayOfMonth)
                }
            }
            .background(
                shape = CircleShape,
                color = if (day.isSelected) {
                    colorResource(R.color.md_theme_light_tertiary)
                } else {
                    Color.Transparent
                }
            )
            // Padding for the "box" for each number
            .padding(13.dp),
    ) {
        if (day.dayOfMonth != 0) {
            Text(
                text = day.dayOfMonth.toString(),
                fontWeight = if (day.isSelected) {
                    FontWeight.Bold
                 } else { null },
                color = if (day.isSelected) {
                    Color.White
                } else {
                    Color.Black
                }
            )
        } else {
            // If the day is 0 then put in empty space
            Spacer(
                modifier = Modifier
                    .background(Color.Transparent)
            )
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun generateDaysInMonth(
    localDay: LocalDate
): List<CalendarDay> {
    val firstDay = localDay.withDayOfMonth(1)
    val lastDay = localDay.withDayOfMonth(localDay.lengthOfMonth())

    // Put in 0 for empty days before the first day of the month
    val daysBefore = (1 until firstDay.dayOfWeek.value).map { day ->
        CalendarDay(dayOfMonth = 0)
    }

    val daysInMonth = (1..localDay.lengthOfMonth()).map { day ->
        CalendarDay(dayOfMonth = day)
    }

    // Put in 0 for empty days after the last day of the month
    val daysAfter = (1..(DayOfWeek.SUNDAY.value - lastDay.dayOfWeek.value)).map { day ->
        CalendarDay(dayOfMonth = 0)
    }

    return daysBefore + daysInMonth + daysAfter
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val infoStore = AppDataStore(context)

    var nameTokenValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val nameTokenText = infoStore.getAccessToken.collectAsState(initial = "")

    Text(
        text = "Personalize your information",
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 2.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(bottom = 20.dp)

    )

    Text(
        text = "Name: " + nameTokenText.value,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    )

    Spacer(modifier = Modifier.height(10.dp))

    TextField(
        value = nameTokenValue.value,
        onValueChange = { nameTokenValue.value = it },
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(R.color.md_theme_light_onPrimaryContainer),
            cursorColor = colorResource(R.color.md_theme_light_onPrimaryContainer),
            focusedIndicatorColor = colorResource(R.color.seed),
            unfocusedIndicatorColor = Color(0xffb8e0f6),
            containerColor = Color.White
        ),
    )

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                infoStore.saveToken(nameTokenValue.value.text)
            }
        },
        colors = ButtonDefaults.buttonColors(colorResource(R.color.md_theme_light_tertiary)),

    ) {
        Text(text = "Update")
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    FinalProjectTheme {
        NavigationBar()
    }
}