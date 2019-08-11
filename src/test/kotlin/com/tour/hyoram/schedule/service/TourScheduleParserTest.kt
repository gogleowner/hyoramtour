package com.tour.hyoram.schedule.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tour.hyoram.schedule.model.*
import org.junit.jupiter.api.Test

internal class TourScheduleParserTest {
    private val tourScheduleParser = TourScheduleParser()

    @Test
    fun parseTourScheduleToDto() {
        val jacksonObjectMapper = jacksonObjectMapper()

        val tourScheduleResponse: MutableList<MutableList<Any>> =
            jacksonObjectMapper.readValue(this::class.java.getResourceAsStream("[\n  [\n    \"1일차\",\n    \"9/2\",\n    \"오전\",\n    \"\",\n    \"\",\n    \"\",\n    \"\",\n    \"여권\\n다이노스카드\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"인천 - 모스크바\",\n    \"비행\",\n    \"- 출발 : 09/03(화) 01:45\\n- 도착 : 09/03(화) 05:15\\n- 항공편 : SU253\\n\\n총 소요시간 : 16시간 45분 (비행시간 : 13시간 25분 , 대기시간 : 03시간 20분)\"\n  ],\n  [\n    \"2일차\",\n    \"9/3\",\n    \"오전\",\n    \"모스크바 - 로마\",\n    \"비행\",\n    \"- 출발 : 09/03(화) 08:35\\n- 도착 : 09/03(화) 11:30\\n- 항공편 ; SU2402\\n\\n총 소요시간 : 16시간 45분 (비행시간 : 13시간 25분 , 대기시간 : 03시간 20분)\",\n    \"\",\n    \"로마 교통권 구입\\n개인이어폰\",\n    \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"공항 - 로마 이동\",\n    \"버스\",\n    \"#공항에서 테르미니역가는방법\\n셔틀버스(70분/6유로)\\n이용 방법 : http://info.hanatour.com/dest/content/know/20?ctype=ALL&contentID=1000020709101\\n예약 홈페이지 : www.sitbusshuttle.com\\n\\n#공항버스 정류장까지 가는 법\\n수화물을 찾고 분홍색 버스 표지판을 따라 공항 밖으로 나와 오른쪽으로 쭉 이동하다 보면 티켓 창구가 보임 \\n티켓 창구에서 시간대와 목적지를 확인한 후 이용할것! 대부분 짐 추가에 따른 별도 요금은 없음\",\n    \"6\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"숙소 체크인, 자유여행\",\n    \"자유\",\n    \"#가볼만한 곳\\n- 공원 : Parco del Colle Oppio\\n- 캄포데이피오리 : 나보나 광장에서 300m 떨어진 광장이자 재래시장\\n- 트라스테베레 : 로마에서 가장 오래된 서민지구 값싼레스토랑이 많이 있는 곳/한국의 연남동\\n\\n- 콜로세움 통합권으로 갈수 있는곳 : 콜로세움, 팔라티노언덕, 포로로마노\",\n    \"3.5\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"야경투어\",\n    \"투어\",\n    \"#투어내용\\n- 투어코스 : 산타 마리아 마죠레 성당 → 콜로세움 → 베네치아 광장 → 나보나 광장 → 천사의 성\\n- 미팅장소 : 산타마리아마죠레 성당 앞 분수대\\n- 투어시간 : 20:00~22:30 (약 2시간 30분 진행)\\n- 예약번호 : TQ 2965565\\n- URL : https://www.myrealtrip.com/offers/31834\\n- 업체명 : 이태리스케치북\\n\\n#추가비용 \\n- 교통비 약 3유로\\n- 수신기 비용 3유로\",\n    \"6\"\n  ],\n  [\n    \"3일차\",\n    \"9/4\",\n    \"오전\",\n    \"시내투어\",\n    \"투어\",\n    \"#투어내용\\n- 투어코스 : 콜로세움/콘스탄티누스 개선문 → 포로로마노 → 캄피돌리오 광장 → 베네치아 광장 → 나보나 광장 → 판테온 → 트레비 분수 → 스페인 광장\\n- 미팅장소 : 테르미니역 안 쪽 1층(지상층) 중앙에 위치한 맥도날드 앞\\n- 투어시간 : 09:00~16:00\\n- 예약번호 : TQ 2965565\\r\\n- URL : https://www.myrealtrip.com/offers/31834\\n- 업체명 : 이태리스케치북\\n\\n#추가비용\\n- 수신기 비용 3유로\",\n    \"3\",\n    \"개인이어폰\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"시내투어\",\n    \"투어\",\n    \"오전투어에 이어서 진행\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"나보나광장, 카타콤베\",\n    \"자유\",\n    \"- 나보나광장 : 로마시대 경기장이 있던 곳. 현재는 경기장관중석 자리에 건물들이 세워져 있음.\\n- 카타콤베 : 기독교 박해시대에 기독교인들의 피난처로 사용된곳. 9-12시or 14-17시 이용가능. 테르미니역에서 A라인으로 갈아타고 San Giovanni 역으로 이동. 218번 버스를 타고 Ardeatiba에서 하차\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"투어장소 다시가기\",\n    \"자유\",\n    \"저녁식사후 야경투어때 가봤던곳 한번 더 구경\"\n  ],\n  [\n    \"4일차\",\n    \"9/5\",\n    \"오전\",\n    \"로마 바티칸 투어\",\n    \"투어\",\n    \"#투어내용\\n- 투어코스 : 피나코테카 (회화관) -> 벨베데레 정원 -> 미술관 내부 -> 라파엘로의 방 -> 시스티나 소성당 -> 베드로 대성당 -> 베드로 광장\\n- 미팅장소 : Cipro, Via Cipro, 00136 Roma RM, 이탈리아(로마 지하철 A선 (빨간색) Cipro역에서 하차 > 바로 앞에 있는 광장에서 온리유럽 가이드를 찾아주세요.)\\n- 투어시간 : 07:30~13:30\\n- URL : https://www.theminda.com/tg/view.php?itemno=5017&category=001003001\\n- 업체명 : 온리유럽\\n\\n#추가비용\\n- 수신기 비용 3유로\\n- 바티칸 박물관 입장료 (17유로)\",\n    \"20\",\n    \"개인이어폰\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"휴식\",\n    \"자유\",\n    \"숙소 혹은 카페에서 휴식\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"자유일정\",\n    \"자유\"\n  ],\n  [\n    \"5일차\",\n    \"9/6\",\n    \"오전\",\n    \"오르비에또 이동, 관광\",\n    \"기차\",\n    \"#로마 테르미니-오르비에또 07:28-08:43/좌석정보알수없음 \\n- 플랫폼은 1-2EST. 10분정도 걸어야함 탑승전 티켓펀칭 필수\",\n    \"10\",\n    \"기차티켓\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"오르비에또 관광\",\n    \"자유\",\n    \"#오르비에토에서 할 수 있는 것\\n- 산악열차타기\\n- 90분 유효버스 푸니쿨라, 1.3유로 (10분마다 수시로 운행)\\ntip 마을 푸니쿨라하차장에서 치비타로가는 버스 탈수있음 (평일만) 타는 곳은 학교 안 주차장\",\n    \"1.3\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"시비타 디 반뇨레쪼 이동\",\n    \"버스\",\n    \"#오르비에토 - 치비타 (1시간반 소요, 14:05 버스 탑승예정)\\n1. 오르비에터도착 후 역 옆가게에서 치비타 욍복티켓(ROUND TRIP) 구입 (4.4유료)\\n2. 버스타는 곳은 어떤 허름한 정류장\\n3. 티켓파는 곳에서 파는 피자 맛있음\",\n    \"4.4\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"시비타 디 반뇨레쪼 관광\",\n    \"자유\",\n    \"천공의 섬 라퓨타의 배경이된 곳\\n마을 입장권 5유로\\nhttps://m.blog.naver.com/iouhoi/221020077948\",\n    \"5\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"오르비에또 이동\",\n    \"버스\",\n    \"#치비타 - 오르비에또 (17:25 버스 탑승 예정)\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"로마 이동\",\n    \"기차\",\n    \"#오르비에또-로마 테르미니 19:34-20:49/COACH3 SEAT 53,55 \"\n  ],\n  [\n    \"6일차\",\n    \"9/7\",\n    \"오전\",\n    \"자유일정\",\n    \"자유\",\n    \"#가볼만한 곳\\n- 콜로세움\\n- 포로로마노\\n- 팔라티노언덕\\n- 카라칼라 욕장\\n+ 무언가\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"자유일정\",\n    \"자유\",\n    \"#가볼만한 곳\\n- 콜로세움\\n- 포로로마노\\n- 팔라티노언덕\\n- 카라칼라 욕장\\n+ 무언가\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"자유일정\",\n    \"자유\",\n    \"#가볼만한 곳\\n- 콜로세움\\n- 포로로마노\\n- 팔라티노언덕\\n- 카라칼라 욕장\\n+ 무언가\"\n  ],\n  [\n    \"7일차\",\n    \"9/8\",\n    \"오전\",\n    \"남부지방 투어\",\n    \"투어\",\n    \"#투어내용\\n- 투어코스 : 폼페이, 소렌토, 아말피, 포지타노\\n- 미팅장소 : officine italia roma repubblica 앞 미팅\\n- 투어시간 : 06:40~17:00\\n- 예약번호 : TQ 2991555\\n- URL : https://www.myrealtrip.com/offers/41977\\n- 업체명 : 인디고트래블\\n\\n#추가비용 \\n- 폼페이 입장료 15유로(만 18세 미만 학생 폼페이 유적지 무료입장 *여권 원본 지참/사본불가) \\n- 점심 식비 (약 15~20유로)\\n- 포지타노 진입 미니버스 (10유로 / 왕복)\\n- 수신기 비용 3유로\\n[[ 스피드보트 15유로 (여름 시즌 선택사항 / 약 30분 진행) ]]\",\n    \"25\",\n    \"개인이어폰\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"남부지방 투어\",\n    \"투어\",\n    \"오전과 동일\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"아말피로 이동\",\n    \"버스\",\n    \"#포지타노-아말피\\n- STA버스 타고 30분, 1.30유로 or \\n- 포지타노에서 페리 이용 25분 6유로 or \\n- 살레르노에서 페리 이용 1시간 4유로\",\n    \"6\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"아말피 숙소 체크인, 휴식\",\n    \"자유\",\n    \"\",\n    \"1.5\",\n    \"\",\n    \"9월 08일– 9월 10일 \\n주소 Salita Ruggiero II 08, Amalfi, Campania 84011, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/18498739\\n체크인/체크아웃 15:00/10:00\\nTourist tax 1.5/명\\nincluded Breakfast \"\n  ],\n  [\n    \"8일차\",\n    \"9/9\",\n    \"종일\",\n    \"아말피에서 해변에서 놀며 휴식\",\n    \"자유\",\n    \"#가볼만한 곳\\n- 두오모 광장\\n\\n#맛집\\n- 피자집 :: Marina Grande\\n- 오징어 튀김 :: Cuoppo d'Amalf\\n- 이탈리안 집 :: 아말피 최고의 맛집. Marina Grande\"\n  ],\n  [\n    \"9일차\",\n    \"9/10\",\n    \"오전\",\n    \"아말피 - 살레르노\",\n    \"버스\",\n    \"#아말피-살레르노\\n- SITA버스로 광장으로 이동, 살레르노가는 버스탑승(1시간15분 소요, 7시에 버스타기)\\n- SITA버스 시간표 https://www.ravello.com/sita-bus-schedule/amalfi-maiori-salerno/\\n- 오른쪽 자리 탑승하면 뷰가 좋음\",\n    \"\",\n    \"기차티켓\",\n    \"9월 10일– 9월 13일\\n주소 Via del Porcellana, 14, 피렌체 (Florence), Toscana 50123, 이탈리아 https://www.airbnb.co.kr/rooms/16170693 \\n체크인/체크아웃 19:00/11:00 Not included Breakfast\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"살레르노 - 피렌체\",\n    \"기차\",\n    \"#살레르노 - 피렌체 산타마리아 노벨라 8:53-12:46/COACH1 SEAT 25,28\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"피렌체 자유여행\",\n    \"자유\",\n    \"#갈수 있는 곳들\\n- 산티미켈레 에 가에타노 성당\\n- 두오모 대성당\\n- 쿠폴라\\n- 죠또의 종탑천국의 문\\n- 공화국 광장(개선문)\\n- 시뇨리아 광장\\n- 베끼오궁전\\n- 로자 데이 란치(회랑)\\n- 우피치미술관\\n- 베끼오다리\\n\\n# 15시 쿠폴라 예약(두오모 통합권 이용)\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"피렌체 야경투어\",\n    \"투어\",\n    \"#투어내용\\n- 투어코스 : 산로렌쪼성당 → 메디치 리카르디 궁전 → 피렌체 두오모 → 조토의 종탑 → 산조반니세례당 → 레푸블리카광장 → 단테의생가 → 시뇨리아광장 → 베키오궁전 → 우피치미술관 → 로지아 데이 란치 → 베키오다리 → 미켈란젤로광장\\r\\n- 미팅장소 : 산로렌쪼성당 우측 (piazza di san lorenzo 2r, firenze)\\n- 투어시간 : 19:30~23:30\\n- 예약번호 : TQ 3677373\\n- URL : https://www.myrealtrip.com/offers/32691\\n- 업체명 : 보따리투어\\n\\n#추가비용 \\n- 버스티켓(1.5유로) - 미켈란젤로광장에서 피렌체 시내 이동시 버스티켓 1매가 필요\\n- 버스티켓은 타바키 또는 피렌체 중앙역 자동판매기에서 24시간 구매 가능하나\\n구매가 번거로우시다면, 1인 1.5유로 지참후 참석하기(잔돈으로 준비)\",\n    \"1.5\"\n  ],\n  [\n    \"10일차\",\n    \"9/11\",\n    \"종일\",\n    \"피렌체 자유여행\",\n    \"자유\"\n  ],\n  [\n    \"11일차\",\n    \"9/12\",\n    \"종일\",\n    \"피렌체 및 피사 자유여행\",\n    \"자유\",\n    \"#산타마리아 노벨라역-Pisa S.rossore역(1시간 소요)\\n- 트랜이탈리아에서 예약할수있고 1-2달 전에 예매 가능한 것으로 보임.역에서 내려 10분 정도 걸으면 됨.\\n- 열차티켓예매는 피렌체에 가서 하기로 함. 시간을 유연하게 변동하기 위해, 지금하나 나중에 하나 가격 변동없이 17.4*2\\n트랜이탈리아 사이트 http://www.trenitalia.com/\"\n  ],\n  [\n    \"12일차\",\n    \"9/13\",\n    \"종일\",\n    \"시에나 투어\",\n    \"투어\",\n    \"#투어내용\\n- 투어코스 : 시에나 -> 피엔짜 -> 반뇨 비뇨니 -> 막시무스 집 -> 산 퀴리코 오르차\\n-  미팅장소 : 피렌체 산타 마리아 노벨라 기차역 (당일 상황에 따라 변경될수 있음)\\n- 투어시간 : 08:20~19:30\\n- 예약번호 : TQ 3114313\\n- URL : https://www.myrealtrip.com/offers/36808\\n- 업체명 : JOON HO LEE\\n\\n#추가비용 \\n- 차량이용료 1인당 80유로 (1인 당일 80유로 현금 결재)\\n- 점심 식사비 현장 직접 유로 현금 결제 (메뉴- 토스카나 정통 코스 현지식 25유로 정도)\\n- 기타 개인 경비\",\n    \"107.5\",\n    \"\",\n    \"9월 13일– 9월 14일 \\n주소 Viale Fratelli Rosselli, 61 TERZO PIANO, 피렌체, 토스카나 50144, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/396714\\n체크인/체크아웃 11:00/11:00\\nTourist tax 2.5/명\\nincluded Breakfast \"\n  ],\n  [\n    \"13일차\",\n    \"9/14\",\n    \"오전\",\n    \"더몰 쇼핑\",\n    \"쇼핑\",\n    \"# 중국버스 타기\",\n    \"\",\n    \"기차티켓\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"피렌체 자유여행\",\n    \"자유\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"피렌체 - 베네치아\",\n    \"기차\",\n    \"#피렌체 산타마리아 노벨라-베네치아 산타루치아 18:54-20:54/COACH6 SEAT 5,6\\n#베네치아 역에서 숙소가는 방법\\nhttps://goo.gl/maps/UyYL65bBFaP164cF8\",\n    \"4\",\n    \"\",\n    \"9월 14일– 9월 17일 \\n주소 Ruga do Pozzi, 4175, 베니스, Veneto 30121, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/396714\\n체크인/체크아웃 16:00/10:00\\nTourist tax 4/명\\nNot  cluded Breakfast \"\n  ],\n  [\n    \"14일차\",\n    \"9/15\",\n    \"종일\",\n    \"돌로미티 투어\",\n    \"투어\",\n    \"URL : http://bigbangtour.co.kr/mypage/tour_order_kcpResult.html\\n예약인원을 모객 중임\"\n  ],\n  [\n    \"15일차\",\n    \"9/16\",\n    \"종일\",\n    \"베네치아 자유여행\",\n    \"자유\",\n    \"#바포레토 48시간권 구입 (금액 ??)\\n\\n#가볼만한 곳\\n- 비알토다리 \\n- 산 마르코 광장 \\n- 산 마르코 성당 \\n- 종탑 \\n- 두칼레궁전 \\n- 탄식의 다리 \\n- 곤돌라\\n- 부라노섬 토르첼로섬 리도섬\\ntip 식료품음 coop에서 사기\"\n  ],\n  [\n    \"16일차\",\n    \"9/17\",\n    \"오전\",\n    \"베네치아 자유여행\",\n    \"자유\",\n    \"#가볼만한 곳\\n- 산 조르조 마조레 성당\\n- 산타 마리아 살루테 성당\\n- 페기 구겐하임 미술관\\n- 아카데미아 미술관\\n\\nTODO 베네치아 세부 투어\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"오후\",\n    \"베네치아 자유여행\",\n    \"자유\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"저녁\",\n    \"베네치아 공항 이동\",\n    \"버스\",\n    \"교통수단 알아봐야함\"\n  ],\n  [\n    \"\",\n    \"\",\n    \"\",\n    \"베네치아 - 모스크바\",\n    \"비행\",\n    \"- 출발 : 09/17(화) 23:55\\n- 도착 : 09/18(수) 04:00\\n- 항공편 : SU2423\\n\\n총 소요시간 : 15시간 50분 (비행시간 : 11시간 55분 , 대기시간 : 03시간 55분 )\"\n  ],\n  [\n    \"17일차\",\n    \"9/18\",\n    \"\",\n    \"모스크바 - 인천\",\n    \"비행\",\n    \"- 출발 : 09/18(수) 07:55\\n- 도착 : 09/18(수) 22:45\\n- 항공편 : SU252\"\n  ]\n]\n"))

        val actual = tourScheduleParser.parseTourScheduleToDto(tourScheduleResponse)

        val expect = jacksonObjectMapper.readValue<List<Schedule>>(
            this::class.java.getResource("[\n  {\n    \"day\": \"1일차\",\n    \"date\": \"9/2\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"여권\\n다이노스카드\",\n    \"room\": \"\",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"\",\n            \"theme\": \"NONE\",\n            \"detail\": \"\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"\",\n            \"theme\": \"NONE\",\n            \"detail\": \"\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"인천 - 모스크바\",\n            \"theme\": \"FLIGHT\",\n            \"detail\": \"- 출발 : 09/03(화) 01:45\\n- 도착 : 09/03(화) 05:15\\n- 항공편 : SU253\\n\\n총 소요시간 : 16시간 45분 (비행시간 : 13시간 25분 , 대기시간 : 03시간 20분)\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"2일차\",\n    \"date\": \"9/3\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"로마 교통권 구입\\n개인이어폰\",\n    \"room\": \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"모스크바 - 로마\",\n            \"theme\": \"FLIGHT\",\n            \"detail\": \"- 출발 : 09/03(화) 08:35\\n- 도착 : 09/03(화) 11:30\\n- 항공편 ; SU2402\\n\\n총 소요시간 : 16시간 45분 (비행시간 : 13시간 25분 , 대기시간 : 03시간 20분)\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"공항 - 로마 이동\",\n            \"theme\": \"BUS\",\n            \"detail\": \"#공항에서 테르미니역가는방법\\n셔틀버스(70분/6유로)\\n이용 방법 : http://info.hanatour.com/dest/content/know/20?ctype=ALL&contentID=1000020709101\\n예약 홈페이지 : www.sitbusshuttle.com\\n\\n#공항버스 정류장까지 가는 법\\n수화물을 찾고 분홍색 버스 표지판을 따라 공항 밖으로 나와 오른쪽으로 쭉 이동하다 보면 티켓 창구가 보임 \\n티켓 창구에서 시간대와 목적지를 확인한 후 이용할것! 대부분 짐 추가에 따른 별도 요금은 없음\"\n          },\n          {\n            \"content\": \"숙소 체크인, 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#가볼만한 곳\\n- 공원 : Parco del Colle Oppio\\n- 캄포데이피오리 : 나보나 광장에서 300m 떨어진 광장이자 재래시장\\n- 트라스테베레 : 로마에서 가장 오래된 서민지구 값싼레스토랑이 많이 있는 곳/한국의 연남동\\n\\n- 콜로세움 통합권으로 갈수 있는곳 : 콜로세움, 팔라티노언덕, 포로로마노\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"야경투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"#투어내용\\n- 투어코스 : 산타 마리아 마죠레 성당 → 콜로세움 → 베네치아 광장 → 나보나 광장 → 천사의 성\\n- 미팅장소 : 산타마리아마죠레 성당 앞 분수대\\n- 투어시간 : 20:00~22:30 (약 2시간 30분 진행)\\n- 예약번호 : TQ 2965565\\n- URL : https://www.myrealtrip.com/offers/31834\\n- 업체명 : 이태리스케치북\\n\\n#추가비용 \\n- 교통비 약 3유로\\n- 수신기 비용 3유로\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"3일차\",\n    \"date\": \"9/4\",\n    \"cost\": 3.0,\n    \"rememberThings\": \"개인이어폰\",\n    \"room\": \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"시내투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"#투어내용\\n- 투어코스 : 콜로세움/콘스탄티누스 개선문 → 포로로마노 → 캄피돌리오 광장 → 베네치아 광장 → 나보나 광장 → 판테온 → 트레비 분수 → 스페인 광장\\n- 미팅장소 : 테르미니역 안 쪽 1층(지상층) 중앙에 위치한 맥도날드 앞\\n- 투어시간 : 09:00~16:00\\n- 예약번호 : TQ 2965565\\r\\n- URL : https://www.myrealtrip.com/offers/31834\\n- 업체명 : 이태리스케치북\\n\\n#추가비용\\n- 수신기 비용 3유로\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"시내투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"오전투어에 이어서 진행\"\n          },\n          {\n            \"content\": \"나보나광장, 카타콤베\",\n            \"theme\": \"FREE\",\n            \"detail\": \"- 나보나광장 : 로마시대 경기장이 있던 곳. 현재는 경기장관중석 자리에 건물들이 세워져 있음.\\n- 카타콤베 : 기독교 박해시대에 기독교인들의 피난처로 사용된곳. 9-12시or 14-17시 이용가능. 테르미니역에서 A라인으로 갈아타고 San Giovanni 역으로 이동. 218번 버스를 타고 Ardeatiba에서 하차\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"투어장소 다시가기\",\n            \"theme\": \"FREE\",\n            \"detail\": \"저녁식사후 야경투어때 가봤던곳 한번 더 구경\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"4일차\",\n    \"date\": \"9/5\",\n    \"cost\": 20.0,\n    \"rememberThings\": \"개인이어폰\",\n    \"room\": \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"로마 바티칸 투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"#투어내용\\n- 투어코스 : 피나코테카 (회화관) -> 벨베데레 정원 -> 미술관 내부 -> 라파엘로의 방 -> 시스티나 소성당 -> 베드로 대성당 -> 베드로 광장\\n- 미팅장소 : Cipro, Via Cipro, 00136 Roma RM, 이탈리아(로마 지하철 A선 (빨간색) Cipro역에서 하차 > 바로 앞에 있는 광장에서 온리유럽 가이드를 찾아주세요.)\\n- 투어시간 : 07:30~13:30\\n- URL : https://www.theminda.com/tg/view.php?itemno=5017&category=001003001\\n- 업체명 : 온리유럽\\n\\n#추가비용\\n- 수신기 비용 3유로\\n- 바티칸 박물관 입장료 (17유로)\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"휴식\",\n            \"theme\": \"FREE\",\n            \"detail\": \"숙소 혹은 카페에서 휴식\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"자유일정\",\n            \"theme\": \"FREE\",\n            \"detail\": \"\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"5일차\",\n    \"date\": \"9/6\",\n    \"cost\": 10.0,\n    \"rememberThings\": \"기차티켓\",\n    \"room\": \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"오르비에또 이동, 관광\",\n            \"theme\": \"TRAIN\",\n            \"detail\": \"#로마 테르미니-오르비에또 07:28-08:43/좌석정보알수없음 \\n- 플랫폼은 1-2EST. 10분정도 걸어야함 탑승전 티켓펀칭 필수\"\n          },\n          {\n            \"content\": \"오르비에또 관광\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#오르비에토에서 할 수 있는 것\\n- 산악열차타기\\n- 90분 유효버스 푸니쿨라, 1.3유로 (10분마다 수시로 운행)\\ntip 마을 푸니쿨라하차장에서 치비타로가는 버스 탈수있음 (평일만) 타는 곳은 학교 안 주차장\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"시비타 디 반뇨레쪼 이동\",\n            \"theme\": \"BUS\",\n            \"detail\": \"#오르비에토 - 치비타 (1시간반 소요, 14:05 버스 탑승예정)\\n1. 오르비에터도착 후 역 옆가게에서 치비타 욍복티켓(ROUND TRIP) 구입 (4.4유료)\\n2. 버스타는 곳은 어떤 허름한 정류장\\n3. 티켓파는 곳에서 파는 피자 맛있음\"\n          },\n          {\n            \"content\": \"시비타 디 반뇨레쪼 관광\",\n            \"theme\": \"FREE\",\n            \"detail\": \"천공의 섬 라퓨타의 배경이된 곳\\n마을 입장권 5유로\\nhttps://m.blog.naver.com/iouhoi/221020077948\"\n          },\n          {\n            \"content\": \"오르비에또 이동\",\n            \"theme\": \"BUS\",\n            \"detail\": \"#치비타 - 오르비에또 (17:25 버스 탑승 예정)\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"로마 이동\",\n            \"theme\": \"TRAIN\",\n            \"detail\": \"#오르비에또-로마 테르미니 19:34-20:49/COACH3 SEAT 53,55 \"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"6일차\",\n    \"date\": \"9/7\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"자유일정\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#가볼만한 곳\\n- 콜로세움\\n- 포로로마노\\n- 팔라티노언덕\\n- 카라칼라 욕장\\n+ 무언가\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"자유일정\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#가볼만한 곳\\n- 콜로세움\\n- 포로로마노\\n- 팔라티노언덕\\n- 카라칼라 욕장\\n+ 무언가\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"자유일정\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#가볼만한 곳\\n- 콜로세움\\n- 포로로마노\\n- 팔라티노언덕\\n- 카라칼라 욕장\\n+ 무언가\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"7일차\",\n    \"date\": \"9/8\",\n    \"cost\": 25.0,\n    \"rememberThings\": \"개인이어폰\",\n    \"room\": \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"남부지방 투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"#투어내용\\n- 투어코스 : 폼페이, 소렌토, 아말피, 포지타노\\n- 미팅장소 : officine italia roma repubblica 앞 미팅\\n- 투어시간 : 06:40~17:00\\n- 예약번호 : TQ 2991555\\n- URL : https://www.myrealtrip.com/offers/41977\\n- 업체명 : 인디고트래블\\n\\n#추가비용 \\n- 폼페이 입장료 15유로(만 18세 미만 학생 폼페이 유적지 무료입장 *여권 원본 지참/사본불가) \\n- 점심 식비 (약 15~20유로)\\n- 포지타노 진입 미니버스 (10유로 / 왕복)\\n- 수신기 비용 3유로\\n[[ 스피드보트 15유로 (여름 시즌 선택사항 / 약 30분 진행) ]]\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"남부지방 투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"오전과 동일\"\n          },\n          {\n            \"content\": \"아말피로 이동\",\n            \"theme\": \"BUS\",\n            \"detail\": \"#포지타노-아말피\\n- STA버스 타고 30분, 1.30유로 or \\n- 포지타노에서 페리 이용 25분 6유로 or \\n- 살레르노에서 페리 이용 1시간 4유로\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"아말피 숙소 체크인, 휴식\",\n            \"theme\": \"FREE\",\n            \"detail\": \"\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"8일차\",\n    \"date\": \"9/9\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 03일 – 9월 08일 \\n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\\n체크인/체크아웃 14:00-22:00/11:00\\nTourist tax 3.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"종일\",\n        \"contents\": [\n          {\n            \"content\": \"아말피에서 해변에서 놀며 휴식\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#가볼만한 곳\\n- 두오모 광장\\n\\n#맛집\\n- 피자집 :: Marina Grande\\n- 오징어 튀김 :: Cuoppo d'Amalf\\n- 이탈리안 집 :: 아말피 최고의 맛집. Marina Grande\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"9일차\",\n    \"date\": \"9/10\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"기차티켓\",\n    \"room\": \"9월 10일– 9월 13일\\n주소 Via del Porcellana, 14, 피렌체 (Florence), Toscana 50123, 이탈리아 https://www.airbnb.co.kr/rooms/16170693 \\n체크인/체크아웃 19:00/11:00 Not included Breakfast\",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"아말피 - 살레르노\",\n            \"theme\": \"BUS\",\n            \"detail\": \"#아말피-살레르노\\n- SITA버스로 광장으로 이동, 살레르노가는 버스탑승(1시간15분 소요, 7시에 버스타기)\\n- SITA버스 시간표 https://www.ravello.com/sita-bus-schedule/amalfi-maiori-salerno/\\n- 오른쪽 자리 탑승하면 뷰가 좋음\"\n          },\n          {\n            \"content\": \"살레르노 - 피렌체\",\n            \"theme\": \"TRAIN\",\n            \"detail\": \"#살레르노 - 피렌체 산타마리아 노벨라 8:53-12:46/COACH1 SEAT 25,28\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"피렌체 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#갈수 있는 곳들\\n- 산티미켈레 에 가에타노 성당\\n- 두오모 대성당\\n- 쿠폴라\\n- 죠또의 종탑천국의 문\\n- 공화국 광장(개선문)\\n- 시뇨리아 광장\\n- 베끼오궁전\\n- 로자 데이 란치(회랑)\\n- 우피치미술관\\n- 베끼오다리\\n\\n# 15시 쿠폴라 예약(두오모 통합권 이용)\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"피렌체 야경투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"#투어내용\\n- 투어코스 : 산로렌쪼성당 → 메디치 리카르디 궁전 → 피렌체 두오모 → 조토의 종탑 → 산조반니세례당 → 레푸블리카광장 → 단테의생가 → 시뇨리아광장 → 베키오궁전 → 우피치미술관 → 로지아 데이 란치 → 베키오다리 → 미켈란젤로광장\\r\\n- 미팅장소 : 산로렌쪼성당 우측 (piazza di san lorenzo 2r, firenze)\\n- 투어시간 : 19:30~23:30\\n- 예약번호 : TQ 3677373\\n- URL : https://www.myrealtrip.com/offers/32691\\n- 업체명 : 보따리투어\\n\\n#추가비용 \\n- 버스티켓(1.5유로) - 미켈란젤로광장에서 피렌체 시내 이동시 버스티켓 1매가 필요\\n- 버스티켓은 타바키 또는 피렌체 중앙역 자동판매기에서 24시간 구매 가능하나\\n구매가 번거로우시다면, 1인 1.5유로 지참후 참석하기(잔돈으로 준비)\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"10일차\",\n    \"date\": \"9/11\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 10일– 9월 13일\\n주소 Via del Porcellana, 14, 피렌체 (Florence), Toscana 50123, 이탈리아 https://www.airbnb.co.kr/rooms/16170693 \\n체크인/체크아웃 19:00/11:00 Not included Breakfast\",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"종일\",\n        \"contents\": [\n          {\n            \"content\": \"피렌체 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"11일차\",\n    \"date\": \"9/12\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 10일– 9월 13일\\n주소 Via del Porcellana, 14, 피렌체 (Florence), Toscana 50123, 이탈리아 https://www.airbnb.co.kr/rooms/16170693 \\n체크인/체크아웃 19:00/11:00 Not included Breakfast\",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"종일\",\n        \"contents\": [\n          {\n            \"content\": \"피렌체 및 피사 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#산타마리아 노벨라역-Pisa S.rossore역(1시간 소요)\\n- 트랜이탈리아에서 예약할수있고 1-2달 전에 예매 가능한 것으로 보임.역에서 내려 10분 정도 걸으면 됨.\\n- 열차티켓예매는 피렌체에 가서 하기로 함. 시간을 유연하게 변동하기 위해, 지금하나 나중에 하나 가격 변동없이 17.4*2\\n트랜이탈리아 사이트 http://www.trenitalia.com/\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"12일차\",\n    \"date\": \"9/13\",\n    \"cost\": 107.5,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 13일– 9월 14일 \\n주소 Viale Fratelli Rosselli, 61 TERZO PIANO, 피렌체, 토스카나 50144, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/396714\\n체크인/체크아웃 11:00/11:00\\nTourist tax 2.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"종일\",\n        \"contents\": [\n          {\n            \"content\": \"시에나 투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"#투어내용\\n- 투어코스 : 시에나 -> 피엔짜 -> 반뇨 비뇨니 -> 막시무스 집 -> 산 퀴리코 오르차\\n-  미팅장소 : 피렌체 산타 마리아 노벨라 기차역 (당일 상황에 따라 변경될수 있음)\\n- 투어시간 : 08:20~19:30\\n- 예약번호 : TQ 3114313\\n- URL : https://www.myrealtrip.com/offers/36808\\n- 업체명 : JOON HO LEE\\n\\n#추가비용 \\n- 차량이용료 1인당 80유로 (1인 당일 80유로 현금 결재)\\n- 점심 식사비 현장 직접 유로 현금 결제 (메뉴- 토스카나 정통 코스 현지식 25유로 정도)\\n- 기타 개인 경비\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"13일차\",\n    \"date\": \"9/14\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"기차티켓\",\n    \"room\": \"9월 13일– 9월 14일 \\n주소 Viale Fratelli Rosselli, 61 TERZO PIANO, 피렌체, 토스카나 50144, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/396714\\n체크인/체크아웃 11:00/11:00\\nTourist tax 2.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"더몰 쇼핑\",\n            \"theme\": \"SHOPPING\",\n            \"detail\": \"# 중국버스 타기\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"피렌체 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"피렌체 - 베네치아\",\n            \"theme\": \"TRAIN\",\n            \"detail\": \"#피렌체 산타마리아 노벨라-베네치아 산타루치아 18:54-20:54/COACH6 SEAT 5,6\\n#베네치아 역에서 숙소가는 방법\\nhttps://goo.gl/maps/UyYL65bBFaP164cF8\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"14일차\",\n    \"date\": \"9/15\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 13일– 9월 14일 \\n주소 Viale Fratelli Rosselli, 61 TERZO PIANO, 피렌체, 토스카나 50144, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/396714\\n체크인/체크아웃 11:00/11:00\\nTourist tax 2.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"종일\",\n        \"contents\": [\n          {\n            \"content\": \"돌로미티 투어\",\n            \"theme\": \"TOUR\",\n            \"detail\": \"URL : http://bigbangtour.co.kr/mypage/tour_order_kcpResult.html\\n예약인원을 모객 중임\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"15일차\",\n    \"date\": \"9/16\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 13일– 9월 14일 \\n주소 Viale Fratelli Rosselli, 61 TERZO PIANO, 피렌체, 토스카나 50144, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/396714\\n체크인/체크아웃 11:00/11:00\\nTourist tax 2.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"종일\",\n        \"contents\": [\n          {\n            \"content\": \"베네치아 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#바포레토 48시간권 구입 (금액 ??)\\n\\n#가볼만한 곳\\n- 비알토다리 \\n- 산 마르코 광장 \\n- 산 마르코 성당 \\n- 종탑 \\n- 두칼레궁전 \\n- 탄식의 다리 \\n- 곤돌라\\n- 부라노섬 토르첼로섬 리도섬\\ntip 식료품음 coop에서 사기\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"16일차\",\n    \"date\": \"9/17\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"9월 13일– 9월 14일 \\n주소 Viale Fratelli Rosselli, 61 TERZO PIANO, 피렌체, 토스카나 50144, 이탈리아\\nhttps://www.airbnb.co.kr/rooms/396714\\n체크인/체크아웃 11:00/11:00\\nTourist tax 2.5/명\\nincluded Breakfast \",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"오전\",\n        \"contents\": [\n          {\n            \"content\": \"베네치아 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"#가볼만한 곳\\n- 산 조르조 마조레 성당\\n- 산타 마리아 살루테 성당\\n- 페기 구겐하임 미술관\\n- 아카데미아 미술관\\n\\nTODO 베네치아 세부 투어\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"오후\",\n        \"contents\": [\n          {\n            \"content\": \"베네치아 자유여행\",\n            \"theme\": \"FREE\",\n            \"detail\": \"\"\n          }\n        ]\n      },\n      {\n        \"timeUnit\": \"저녁\",\n        \"contents\": [\n          {\n            \"content\": \"베네치아 공항 이동\",\n            \"theme\": \"BUS\",\n            \"detail\": \"교통수단 알아봐야함\"\n          },\n          {\n            \"content\": \"베네치아 - 모스크바\",\n            \"theme\": \"FLIGHT\",\n            \"detail\": \"- 출발 : 09/17(화) 23:55\\n- 도착 : 09/18(수) 04:00\\n- 항공편 : SU2423\\n\\n총 소요시간 : 15시간 50분 (비행시간 : 11시간 55분 , 대기시간 : 03시간 55분 )\"\n          }\n        ]\n      }\n    ]\n  },\n  {\n    \"day\": \"17일차\",\n    \"date\": \"9/18\",\n    \"cost\": 0.0,\n    \"rememberThings\": \"\",\n    \"room\": \"\",\n    \"dailySchedules\": [\n      {\n        \"timeUnit\": \"\",\n        \"contents\": [\n          {\n            \"content\": \"모스크바 - 인천\",\n            \"theme\": \"FLIGHT\",\n            \"detail\": \"- 출발 : 09/18(수) 07:55\\n- 도착 : 09/18(수) 22:45\\n- 항공편 : SU252\"\n          }\n        ]\n      }\n    ]\n  }\n]\n").readText())

        assert(actual == expect)
    }
}
