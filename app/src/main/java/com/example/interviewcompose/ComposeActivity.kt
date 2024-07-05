package com.example.interviewcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interviewcompose.ui.theme.InterviewComposeTheme
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

class ComposeActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterviewComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel = ComposeActivityVM()
                    val sheetState =
                        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
                    val coroutineScope = rememberCoroutineScope()

                    ShowBottomSheet(viewModel, sheetState, coroutineScope)
                }
            }
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun MainContent(viewModel: ComposeActivityVM) {

    val state = rememberCollapsingToolbarScaffoldState()
    val pagerState = rememberPagerState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect {
            focusManager.clearFocus()
            viewModel.updateSelectedTab(it)
        }
    }

    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbarModifier = Modifier
            .fillMaxWidth(),

        toolbar = {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                HorizontalPager(
                    pageCount = 3,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(200.dp)
                ) {

                    Image(
                        painter = painterResource(id = viewModel.getTab(it).drawable),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = 3,
                )
            }
        }
    ) {

        Column {

            TextField(
                value = viewModel.userInput.value,
                onValueChange = viewModel::setUserInput,
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = colorResource(id = R.color.bg_color),
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp)
            ) {

                items(viewModel.currentlist) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.tm_core_color_blue_90) // Use primary color from the theme
                        )
                    ) {

                        Row {
                            Image(
                                painter = painterResource(id = it.img),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .size(40.dp),
                                contentScale = ContentScale.Crop
                            )

                            Column {

                                Text(
                                    text = it.body,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                Text(
                                    text = it.title, modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp)
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomSheet(
    viewModel: ComposeActivityVM,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            text = "Statistics",
            style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
        )
        Row {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "Total list size :",
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "${viewModel.currentlist.size}",
                style = TextStyle(fontSize = 20.sp)
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            text = "Max occurring elements",
            style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
        )

        if (viewModel.getStatistics() != null) {
            Text(
                modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                text = viewModel.getStatistics()!![0],
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                text = viewModel.getStatistics()!![1],
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier.padding(
                    start = 25.dp,
                    top = 15.dp,
                    bottom = 15.dp
                ),
                text = viewModel.getStatistics()!![2],
                style = TextStyle(fontSize = 20.sp)
            )
        } else {
            Text(
                modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                text = "NA",
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                text = "NA",
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier.padding(
                    start = 25.dp,
                    top = 15.dp,
                    bottom = 15.dp
                ), text = "NA", style = TextStyle(fontSize = 20.sp)
            )
        }
    }) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.show()
                        }
                    },
                    shape = CircleShape,
                    containerColor = colorResource(id = R.color.my_light_primary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_more_vert_24),
                        contentDescription = "open statistics dialog"
                    )
                }
            }
        ) { MainContent(viewModel) }
    }
}


