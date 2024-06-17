package com.example.moscowquiz

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.moscowquiz.ui.theme.MoscowQuizTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Boolean.toInt() = if (this) 1f else 0f

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val localUriHandler = LocalUriHandler.current
            MoscowQuizTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val state = remember { LazyListState() }
                val menuState by remember {
                    derivedStateOf {
                        !(drawerState.isAnimationRunning || drawerState.isOpen)
                    }
                }
                Scaffold(
                    topBar = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                                      },
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent),
                            modifier = Modifier
                                .alpha((menuState).toInt())
                        ) {
                            Icon(
                                Icons.Default.Menu, contentDescription = "menu",
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.Transparent),
                                tint = Color.Red

                            )
                        }
                    }
                ) {

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet(
                                modifier = Modifier.fillMaxHeight(),
                                drawerShape = RectangleShape,
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Row(
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        ) {
                                            NavigationDrawerItem(
                                                label = {
                                                    Text(
                                                        text = "О проекте",
                                                        color = Color.Black,
                                                        textAlign = TextAlign.Start,
                                                        fontSize = 16.sp,
                                                        fontFamily = FontFamily.SansSerif,
                                                        fontWeight = FontWeight.SemiBold,
                                                    )
                                                },
                                                selected = false,
                                                onClick = {
                                                    scope.launch {
                                                        drawerState.close()
                                                        state.animateScrollToItem(3)
                                                    }
                                                },
                                                modifier = Modifier
                                                    .size(width = 320.dp, height = 50.dp)
                                            )
                                            IconButton(
                                                onClick = { scope.launch { drawerState.close() } },
                                                modifier = Modifier
                                            ) {
                                                Icon(
                                                    Icons.Default.Close,
                                                    contentDescription = "menu",
                                                    modifier = Modifier
                                                        .size(40.dp),
                                                    tint = Color.Black

                                                )
                                            }
                                        }
                                        NavigationDrawerItem(
                                            label = {
                                                Text(
                                                    text = "Квизы",
                                                    color = Color.Black,
                                                    textAlign = TextAlign.Start,
                                                    fontSize = 16.sp,
                                                    fontFamily = FontFamily.SansSerif,
                                                    fontWeight = FontWeight.SemiBold,
                                                )
                                            },
                                            selected = false,
                                            onClick = {
                                                scope.launch {
                                                    drawerState.close()
                                                    state.animateScrollToItem(4)
                                                }
                                            },
                                            modifier = Modifier
                                                .size(width = 320.dp, height = 50.dp)
                                        )
                                        NavigationDrawerItem(
                                            label = {
                                                Text(
                                                    text = "Контакты",
                                                    color = Color.Black,
                                                    textAlign = TextAlign.Start,
                                                    fontSize = 16.sp,
                                                    fontFamily = FontFamily.SansSerif,
                                                    fontWeight = FontWeight.SemiBold,
                                                )
                                            },
                                            selected = false,
                                            onClick = {
                                                scope.launch {
                                                    drawerState.close()
                                                    state.animateScrollToItem(6)
                                                }
                                            },
                                            modifier = Modifier
                                                .size(width = 320.dp, height = 50.dp)
                                        )
                                    }
                                    IconButton(
                                        onClick = { localUriHandler.openUri("https://vk.com/public208285942") },
                                        modifier = Modifier
                                            .size(50.dp)
                                            .align(Alignment.End)
                                            .padding(horizontal = 5.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.vk),
                                            contentDescription = "vk",
                                            modifier = Modifier
                                                .size(50.dp)
                                        )
                                    }
                                }
                            }
                        }
                    )//менюшка
                    {

                        LazyColumn(
                            modifier = Modifier
                                .background(Color.White),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            state = state
                        ) {
                            item {
                                Box {
                                    Image(
                                        painter = painterResource(id = R.drawable.gradient),
                                        contentDescription = "gradient",
                                        modifier = Modifier
                                            .size(width = 2000.dp, height = 500.dp)
                                            .align(Alignment.TopStart)
                                            .offset(x = (-20).dp)
                                    )

                                    Column(
                                        modifier = Modifier,
                                        verticalArrangement = Arrangement.Bottom,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.oblojka),
                                            contentDescription = "karta",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .size(400.dp)
                                        )
                                        Text(
                                            text = "Интерактивный проект \n «Горжусь тобой, Москва»",
                                            color = Color.Black,
                                            textAlign = TextAlign.Center,
                                            fontSize = 25.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier
                                                .padding(horizontal = 30.dp),
                                            style = LocalTextStyle.current.merge(
                                                TextStyle(
                                                    lineHeight = 1.5.em,
                                                    platformStyle = PlatformTextStyle(
                                                        includeFontPadding = false
                                                    ),
                                                    lineHeightStyle = LineHeightStyle(
                                                        alignment = LineHeightStyle.Alignment.Center,
                                                        trim = LineHeightStyle.Trim.None
                                                    )
                                                )
                                            )
                                        )
                                        Text(
                                            text = "Тематические интеллектуальные \n игры о городе Москва - серия квизов \n с вопросами на знание истории,\n культуры, географии столицы России",
                                            color = Color.Black,
                                            textAlign = TextAlign.Center,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.Light,
                                            fontSize = 18.sp,
                                            modifier = Modifier
                                                .padding(horizontal = 30.dp, vertical = 15.dp)
                                        )
                                    }
                                } // карта и (текст х2)
                            }

                            item {
                                Button(
                                    onClick = { " " },
                                    modifier = Modifier
                                        .padding(top = 30.dp)
                                        .background(colorResource(id = R.color.sin))
                                        .size(width = 219.dp, height = 49.dp),
                                    shape = RoundedCornerShape(
                                        topStart = 0.dp,
                                        topEnd = 0.dp,
                                        bottomEnd = 0.dp,
                                        bottomStart = 0.dp,
                                    ),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(
                                            id = R.color.sin
                                        )
                                    )
                                ) {
                                    Text(
                                        text = "ПРОЙТИ КВИЗЫ",
                                        color = Color.White,
                                        textAlign = TextAlign.Start,
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                } // лжекнопка
                            }

                            item {
                                IconButton(
                                    onClick = { localUriHandler.openUri("https://vk.com/public208285942") },
                                    modifier = Modifier
                                        .padding(horizontal = 160.dp)
                                        .padding(top = 25.dp)
                                        .padding(bottom = 50.dp)
                                        .size(30.dp),
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.vk),
                                        contentDescription = "vk",
                                        modifier = Modifier
                                            .size(30.dp)
                                    )
                                } // настроить ссылку на вк
                            }

                            item {
                                Text(
                                    text = "Проект направлен на расширение \n знаний обучающихся о столице России: \n её истории, достопримечательностях, \nкультурной составляющей, \n особенностях территориального \n расположения, известных личностях, \n которых подарила Москва миру. ",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .padding(top = 50.dp),
                                )
                                Text(
                                    text = "Проект предполагает проведение на \n постоянной основе тематических \n интеллектуальных игр о городе для \n школьников и студентов в офлайн и \n онлайн-формате.",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .padding(top = 20.dp)
                                        .padding(bottom = 50.dp)
                                )
                            }

                            item {
                                Text(
                                    text = "Правила",
                                    color = Color.Black,
                                    fontSize = 30.sp,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp)
                                        .padding(top = 50.dp)
                                        .padding(bottom = 20.dp)
                                )
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.time),
                                        contentDescription = "time",
                                        modifier = Modifier
                                            .padding(horizontal = 150.dp)
                                            .padding(top = 20.dp)
                                            .padding(bottom = 15.dp)
                                            .size(100.dp)
                                    )
                                    Text(
                                        text = "30 сек на каждый вопрос",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(horizontal = 80.dp)
                                            .padding(top = 15.dp)
                                            .padding(bottom = 20.dp)
                                    )
                                }
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.what),
                                        contentDescription = "what",
                                        modifier = Modifier
                                            .padding(horizontal = 150.dp)
                                            .padding(top = 20.dp)
                                            .padding(bottom = 15.dp)
                                            .size(100.dp)
                                    )
                                    Text(
                                        text = "5 квизов по 10 вопросов",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(horizontal = 80.dp)
                                            .padding(top = 15.dp)
                                            .padding(bottom = 20.dp)
                                    )
                                }
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.chat),
                                        contentDescription = "chat",
                                        modifier = Modifier
                                            .padding(horizontal = 150.dp)
                                            .padding(top = 20.dp)
                                            .padding(bottom = 15.dp)
                                            .size(100.dp)
                                    )
                                    Text(
                                        text = "4 варианта ответа - один правильный",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(horizontal = 80.dp)
                                            .padding(top = 15.dp)
                                            .padding(bottom = 60.dp)
                                    )
                                } // правила
                            }

                            item {
                                Text(
                                    text = "Реализация проекта в образовательных организациях",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 38.sp,
                                    softWrap = true,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .padding(top = 50.dp)
                                        .padding(bottom = 20.dp),
                                    style = LocalTextStyle.current.merge(
                                        TextStyle(
                                            lineHeight = 1.5.em,
                                            platformStyle = PlatformTextStyle(
                                                includeFontPadding = false
                                            ),
                                            lineHeightStyle = LineHeightStyle(
                                                alignment = LineHeightStyle.Alignment.Center,
                                                trim = LineHeightStyle.Trim.None
                                            )
                                        )
                                    ),
                                    overflow = TextOverflow.Ellipsis
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.granty),
                                    contentDescription = "granty",
                                    modifier = Modifier
                                        .padding(top = 50.dp)
                                        .padding(bottom = 20.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.img),
                                    contentDescription = "komitet",
                                    modifier = Modifier
                                        .padding(top = 50.dp)
                                        .padding(bottom = 20.dp)
                                )
                            }

                            item {
                                Text(
                                    text = "Контакты",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 30.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp)
                                        .padding(top = 50.dp)
                                        .padding(bottom = 20.dp)
                                )
                            }

                            item {
                                Box {
                                    TextButton(
                                        onClick = { localUriHandler.openUri("mailto:mail@mastertalant.ru") },
                                        modifier = Modifier
                                            .size(width = 165.55.dp, height = 23.dp)

                                    ) {
                                    }
                                    Text(
                                        text = "mail@mastertalant.ru",
                                        color = colorResource(id = R.color.coral),
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Light,

                                        )
                                }
                            }

                            item {
                                Text(
                                    text = "Проект реализуется АНО \"Мастерская талантов\"",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp, vertical = 15.dp)
                                )
                            }

                            item {
                                Box {
                                    TextButton(
                                        onClick = { localUriHandler.openUri("https://disk.yandex.ru/i/mLRzW4FTa7KM3Q") },
                                        modifier = Modifier
                                            .size(width = 137.83.dp, height = 20.dp)
                                            .padding(horizontal = 30.dp)
                                            .padding(top = 15.dp)
                                            .padding(bottom = 60.dp)
                                    ) {
                                    }
                                    Text(
                                        text = "Положение о проекте",
                                        color = colorResource(id = R.color.coral),
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Light,
                                        modifier = Modifier
                                            .padding(horizontal = 30.dp)
                                            .padding(top = 15.dp)
                                            .padding(bottom = 60.dp)
                                    )
                                }
                            }
                        } //общий колумн
                    }
                }
            }
        }
    }
}
