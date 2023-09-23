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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import com.example.chattingappui.data.imageList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StatusScreen(navHostController: NavHostController) {
    val person =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Person>("data") ?: Person()

/*    StoriesComponent(imageList) {
        navHostController.popBackStack()
    }*/
    MyNewStoryComponent(imageList)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyNewStoryComponent(
    listOfImages: List<Int>,
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
            listOfImages[it]
        }
    ) {
        ImageTouchChecker(
            image = listOfImages[it],
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

    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(listOfImages.size) { iteration ->
/*            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(20.dp)
            )*/
            Log.i("VJAY", "LinearIndicator called")
            LinearIndicator(
                modifier = Modifier.weight(1f),
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
fun LinearIndicatorNew(
    modifier: Modifier = Modifier,
    startProgress: Boolean = false,
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
            }
            onAnimationEnd()
        }
    }

    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(12.dp)),
        color = Color.Red,
        trackColor = Color.Green
    )
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
        contentScale = ContentScale.Crop,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoriesComponent(
    listOfImages: List<Int>,
    onLastImage: () -> Unit,
) {
    val pagerState = rememberPagerState {
        listOfImages.size
    }

    var job: Job? by remember {
        mutableStateOf(null)
    }

    //pagerState had to be called from suspend function or coroutine builder
    val coroutineScope = rememberCoroutineScope()

    var currentPage by remember {
        mutableIntStateOf(pagerState.currentPage)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        StoryImage_2(pagerState = pagerState, listOfImages = listOfImages)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.padding(4.dp))

            listOfImages.forEachIndexed { index, i ->
                LinearIndicator(
                    modifier = Modifier.weight(1f),
                    startProgress = index == currentPage,
                ) {
                    Log.i("VIJAY", "index $index")
                    job = coroutineScope.launch {

                        currentPage = pagerState.currentPage

                        if (currentPage < listOfImages.size - 1) {
                            currentPage++
                        } else if (currentPage == listOfImages.size - 1) {
                            onLastImage()
                        }
                        pagerState.animateScrollToPage(currentPage)
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryImage(
    pagerState: PagerState,
    listOfImages: List<Int>
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false
    ) {
        Image(
            painter = painterResource(id = listOfImages[it]),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryImage_2(
    pagerState: PagerState,
    listOfImages: List<Int>
) {
    HorizontalPager(
        state = pagerState,
        key = {
            listOfImages[it]
        }
    ) {
        Image(
            painter = painterResource(id = listOfImages[it]),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
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
        color = Color.Red,
        trackColor = Color.Green
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