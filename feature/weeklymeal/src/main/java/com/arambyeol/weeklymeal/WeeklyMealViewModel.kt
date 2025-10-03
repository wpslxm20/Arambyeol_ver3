package com.arambyeol.weeklymeal

import androidx.lifecycle.ViewModel
import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.entity.MealType
import com.arambyeol.domain.entity.Menu
import com.arambyeol.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeeklyMealViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Meal>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Meal>>> = _uiState

    private val _selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    fun onDateSelected(date: LocalDate) {
        _selectedDate.value = date
    }

    fun loadDummyWeeklyMeals() {
        val mealList = listOf(
            Meal(
                date = "2025-09-29",
                menusByMealType = mapOf(
                    MealType.DINNER to listOf(
                        Menu(16, "쌀밥", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(603, "콩가루배추국", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(511, "적어구이*양념장", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(499, "미니돈까스*케찹", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(200, "오이양파무침", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(117, "미숫가루", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                    ),
                    MealType.BREAKFAST to listOf(
                        Menu(1, "쌀밥 / 누룽지", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(419, "홍고추콩나물국", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(523, "돈육모듬장조림", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(407, "꽈리고추멸치볶음", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(585, "만다린샐러드*키위D", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(171, "페퍼로니피자빵", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(172, "시리얼", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(10, "떠먹는요거트", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(585, "만다린샐러드*키위D", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(833, "크레미게살죽", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(407, "꽈리고추멸치볶음", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(585, "만다린샐러드*키위D", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(64, "샌드위치", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(196, "시리얼바", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                    ),
                    MealType.LUNCH to listOf(
                        Menu(16, "쌀밥", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(25, "순두부찌개", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(186, "한식잡채", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(538, "부추겉절이", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(129, "망고주스", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(417, "얼큰쌀국수", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(864, "설탕꽈배기", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(865, "삶은계란부추겉절이", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(129, "망고주스", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(877, "할라피뇨크림미트볼", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                    )
                )
            ),
            Meal(
                date = "2025-09-30",
                menusByMealType = mapOf(
                    MealType.DINNER to listOf(
                        Menu(16, "쌀밥", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(205, "소고기무국", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(718, "스리라차마요미트볼", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(32, "온두부*양념장", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(664, "블루베리샐러드", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(129, "망고주스", MealType.DINNER.name, "A코스/한식", "", 0.0, 0)
                    ),
                    MealType.BREAKFAST to listOf(
                        Menu(1, "쌀밥 / 누룽지", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(318, "오징어무국", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(144, "물만두찜*초간장", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(206, "고추참치볶음", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(525, "그린샐러드*자두D", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(344, "브라운브레드*버터", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(209, "양송이스프", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(947, "요거트/해쉬브라운", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(525, "그린샐러드*자두D", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(327, "참치미역죽", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(144, "물만두찜*초간장", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(525, "그린샐러드*자두D", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(40, "선식", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(177, "초코도너츠", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(196, "시리얼바", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0)
                    ),
                    MealType.LUNCH to listOf(
                        Menu(275, "흑미밥", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(35, "옥수수스프", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(948, "파인함박스테이크*애플그레이비S", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(109, "바질페스토샐러드파스타", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(949, "수제비트무피클", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(950, "얼라이브자몽", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(406, "*특식*", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0)
                    )
                )
            ),
            Meal(
                date = "2025-10-01",
                menusByMealType = mapOf(
                    MealType.DINNER to listOf(
                        Menu(16, "쌀밥", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(239, "부대찌개", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(549, "가자미구이*와사비장", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(186, "한식잡채", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(70, "깍두기", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(156, "키위주스", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                        Menu(447, "그린샐러드", MealType.DINNER.name, "A코스/한식", "", 0.0, 0)
                    ),
                    MealType.BREAKFAST to listOf(
                        Menu(1, "쌀밥 / 누룽지", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(50, "근대된장국", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(233, "소시지야채볶음", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(137, "미나리무생채", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(542, "시나몬프렌치토스트", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(192, "크림스프", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(61, "후랑크구이", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(174, "꽃맛살샐러드", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(833, "크레미게살죽", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(199, "알감자버터구이", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(137, "미나리무생채", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(849, "빅스위트데니쉬", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(65, "짜요짜요", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(15, "견과류", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(409, "알감자조림", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0)
                    ),
                    MealType.LUNCH to listOf(
                        Menu(16, "쌀밥", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(66, "수제비국", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(311, "연두부*양념장", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(320, "유부겨자냉채", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(111, "복숭아홍차", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(951, "얼큰김치어묵우동", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(362, "핫도그*케찹", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(320, "유부겨자냉채", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(178, "삶은계란", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(70, "깍두기", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(111, "복숭아홍차", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(675, "오꼬노미꼬마돈까스", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0)
                    )
                )
            ),
            Meal(
                date = "2025-10-02",
                menusByMealType = mapOf(
                    MealType.BREAKFAST to listOf(
                        Menu(1, "쌀밥 / 누룽지", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(2, "감자맑은국", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(168, "돼지고기장조림", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(33, "미역줄기볶음", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                        Menu(191, "플레인베이글*버터", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(172, "시리얼", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(733, "치킨너겟*머스타드", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(630, "할라피뇨샐러드", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                        Menu(211, "소고기야채죽", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(733, "치킨너겟*머스타드", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(33, "미역줄기볶음", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                        Menu(64, "샌드위치", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(196, "시리얼바", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(7, "모듬음료", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                        Menu(943, "마늘쫑볶음", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0)
                    ),
                    MealType.LUNCH to listOf(
                        Menu(16, "쌀밥", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(138, "육개장", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(487, "베이컨두부조림", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(259, "봉어묵콩나물찜", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(223, "그린빈스알마늘볶음", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(952, "불맛짜장덮밥*계란후라이", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(421, "야채춘권*칠리S", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(619, "샐러드", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(478, "유부장국", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(6, "배추김치", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0),
                        Menu(321, "아이스홍시", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                        Menu(321, "아이스홍시", MealType.LUNCH.name, "B코스/일품", "", 0.0, 0)
                    )
                )
            )
        )
        _uiState.value = UiState.Success(mealList)
    }
}