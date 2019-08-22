# hyoramtour
이탈리아 여행 일정 보기 앱

## API build & execute

```
$ gradle build
$ docker -t gogleowner/hyoramtour-api:{tag} .
$ docker run -d -p 8080:8080 gogleowner/hyoramtour-api:{tag}
```

## TODO
1. 구글 스프레드 시트 API를 이용하여 시트의 내용을 반환하는 API를 작성해야 하여 튜토리얼을 따라가보자.
    - Google Sheet Api : https://developers.google.com/sheets/api/
    - Google Sheet Api Quick Start : https://developers.google.com/sheets/api/quickstart/java
    - https://github.com/gsuitedevs/java-samples/blob/master/sheets/quickstart/src/main/java/SheetsQuickstart.java

2. 서버에서 구글 시트 API를 사용하도록 하려면, OAuth2 인증이 필요하다.
    - https://developers.google.com/identity/protocols/OAuth2WebServer
    - https://jojoldu.tistory.com/168 
    - 동시에 안드로이드 앱에서도 구글 앱 인증이 필요하기에 안드로이드에서도 인증을 해줘야 한다.
        - https://developers.google.com/identity/sign-in/android/backend-auth
 
3. OAuth2 인증 없이 public web 으로 공유할 수 있는 방법도 있긴 하다. 
    - https://www.youtube.com/watch?v=40pqM2W7vhk
    - ~~우선 인증 없이 이방법으로 진행하여 서버,앱 개발까지 완료한 후 인증도 구현하자.~~
    - 구글 app script를 서비스수준의 API를 만드는 강력한 기능이 있는데 API접속시 최초 인증이 필요하다.

4. 구글 시트 API 사용시 OAuth2 말고 서비스 키를 발급받아서도 가능하다.
    - https://developers.google.com/sheets/api/guides/authorizing
    - https://console.developers.google.com/apis/api/sheets.googleapis.com/credentials
    - 이 방법을 선택해서 구현해보자.


## android references
- https://android.jlelse.eu/easily-adding-nested-recycler-view-in-android-a7e9f7f04047

