package com.example.chattingappui.ui.theme.screens

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.data.Person
import com.example.chattingappui.data.Status
import com.example.chattingappui.data.imageList
import com.example.chattingappui.data.statusList
import com.example.chattingappui.ui.theme.Gray
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StatusScreen(navHostController: NavHostController) {
    val person =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Person>("data") ?: Person()

/*    val systemUiController: SystemUiController = rememberSystemUiController()
    DisposableEffect(key1 = true) {
        systemUiController.isStatusBarVisible = false // Status bar
        onDispose {
            systemUiController.isStatusBarVisible = true // Status bar
        }
    }*/

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.TopCenter
    ) {
        MyNewStoryComponent(statusList)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyNewStoryComponent(
    listOfImages: List<Status>,
    onLastImage: () -> Unit = {},
) {
    val pagerState = rememberPagerState {
        listOfImages.size
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        // Collect from the a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            // Do something with each page change, for example:
            // viewModel.sendPageSelectedEvent(page)
            Log.d("VIJAY", "Page changed to $page")
        }
    }

    HorizontalPager(
        state = pagerState,
        key = {
            listOfImages[it].id
        }
    ) {

        Box(
            modifier = Modifier
                .padding(top = 50.dp)
                .background(color = Color.White)
        ) {
            ImageTouchChecker(
                image = listOfImages[it].icon,
                modifier = Modifier.fillMaxSize(),
                onLeftSideTouched = {
                    Log.i("VIJAY", "onLeftSideTouched")
                    coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage - 1)
                    }
                },
                onRightSideTouched = {
                    Log.i("VIJAY", "onRightSideTouched")
                    coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                }
            )
        }
    }

    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(top = 14.dp, start = 10.dp, end = 5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(listOfImages.size) { iteration ->
            Log.i("VJAY", "LinearIndicator called")
            LinearIndicator(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                startProgress = pagerState.currentPage == iteration,
            ) {
                coroutineScope.launch {
                    Log.i("VJAY", "current page ${pagerState.currentPage}")
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }
            }
        }
    }
}

@Composable
fun ImageTouchChecker(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    onLeftSideTouched: () -> Unit,
    onRightSideTouched: () -> Unit
) {
    // Get the width of the Image.
    var imageWidth by remember { mutableStateOf(0) }

    // Get the touch position.
    var touchPosition by remember { mutableStateOf(Offset.Zero) }

    Image(
        painter = painterResource(id = image),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = modifier
            .onSizeChanged { imageWidth = it.width }
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    touchPosition = offset

                    // Scale the touch position to the width of the Image.
                    val scaledTouchPosition = touchPosition.x / imageWidth

                    // Check if the right or left side of the Image is touched.
                    if (scaledTouchPosition > 0.5f) {
                        onRightSideTouched()
                    } else {
                        onLeftSideTouched()
                    }
                }
            }
    )
}

@Composable
fun LinearIndicator(
    modifier: Modifier = Modifier,
    startProgress: Boolean = false,
    stopProgress: Boolean = false,
    onAnimationEnd: () -> Unit = {}
) {
    var progress by remember {
        mutableFloatStateOf(0.00f)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )

    if (startProgress) {
        LaunchedEffect(key1 = Unit) {
            while (progress < 1f) {
                progress += 0.01f
                delay(50)  //=> 5sec - 5000/100
                if (stopProgress)
                    break
            }
            onAnimationEnd()
        }
    }

    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(12.dp)),
        color = Color.White,
        trackColor = Gray
    )
}

@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreviewStatusScreen() {
    StatusScreen(rememberNavController())
}