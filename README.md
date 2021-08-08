# java miniProject(Movie)_develop.ver

플레이데이터 팀프로젝트 결과물을 수정 및 디벨롭하여 완성한 코드입니다.
- 상영관별 상영 영화 매핑
- 특정 상영관의 영화 예매시 예약 좌석 수, 잔여 좌석 수 연산
- 매핑 되지 않은 상영관-영화 예매 불가(예외 처리)
- 잔여 좌석이 없는 상영관 예매 불가(예외 처리)
등을 추가로 구현하였습니다.</br>

🚩 팀원분들과 함께 만든 결과물은 [여기](https://github.com/ptkeb/20210805-miniproject)에서 확인해보실 수 있습니다. 

## 📽 미니 프로젝트(Movie) 개요
: Movie(영화) DTO, Theater(상영관) DTO, Reservation(예약) DTO와 MVC 기반 CRUD 구현
- MVC, DTO pattern 설계
- Singleton design 적용
- Exception 처리
- log4j 활용 log 데이터 적재
- jUnit 활용 test

## ➰ Model
- MovieModel : 가상 DB, MovieList/TheaterList/ReservationList 생성 및 할당, HashMap<Theater,Movie>movieInfo로 상영관별 상영 영화 매핑
- Movie(DTO) : 영화 정보 객체
- Theater(DTO) : 상영관 정보 객체
- Reservation(DTO) : 예약 정보 객체

## ➰ Controller & Service
- 전체 리스트 검색(영화/상영관/예약내역/상영관별 상영영화)
- 입력값으로 단일 검색(영화제목으로 영화 검색/상영관 번호로 상영관 검색/예약자 이름으로 예약내역 검색)
- 새로운 예약내역 저장 및 예외 처리
- 기존 예약내역 수정(상영관 및 영화 변경) 및 예외 처리
- 기존 예약내역 삭제 및 예외 처리

## ➰ View
- StartView : 시작 화면, 입력 값에 따른 기능 실행, 로그 기록 적재("C:\\playdata\\20210628_lab\\88.log\\playdata.log"에 저장)
- EndView : 기능 실행 후 출력 화면
- FailView : 에러 발생 시 출력 화면

## 💥 Exception
- DuplicationException : 중복 관련 예외 처리
- NotExistException : 미존재 데이터 관련 예외 처리
- overBookedException : 잔여 좌석 초과 예약 시도 관련 예외 처리

## ✔ 파일 구성
![image](https://user-images.githubusercontent.com/68639271/128504290-f1dd3ec6-bebf-4b22-bad6-a329ac9db37e.png)
