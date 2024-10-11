![header](https://capsule-render.vercel.app/api?type=soft&color=FFF9EB&height=100&section=header&text=Java_Onboarding_Project💬&fontSize=60&fontAlignY=55)

## 📝요구사항
- [x]  Junit를 이용한 테스트 코드 작성법 이해
- [x]  Spring Security를 이용한 Filter에 대한 이해
- [x]  JWT와 구체적인 알고리즘의 이해
- [x]  PR 날려보기
- [x]  리뷰 바탕으로 개선하기
- [x]  EC2에 배포해보기

## 🤓관련 이론
### Spring Security 기본 이해
<details>
<summary>Filter란 무엇인가?(with Interceptor, AOP)</summary>
<div markdown="1">
  
![image](https://github.com/user-attachments/assets/9746f498-ebb4-468a-918b-450da1b01931)
  
Interceptor와 Filter는 Servlet 단위에서 실행되는 반면 AOP는 메소드 앞에 Proxy패턴의 형태로 실행
 
1. 서버를 실행시켜 서블릿이 올라오는 동안에 init이 실행되고, 그 후 doFilter가 실행
2. 컨트롤러에 들어가기 전 preHandler 실행
3. 컨트롤러에서 나와 postHandler, after Completion, doFilter 순으로 진행
4. 서블릿 종료 시 destroy가 실행
  
<br><br>
**Filter**

필터는 스프링 컨텍스트 외부에 존재한다. 
DispatcherServlet 이전에 실행되며, 스프링과 무관하게 지정된 자원에 대해 동작한다.
 
필터가 동작하도록 지정된 자원의 앞단에서 요청내용을 변경하거나, 여러가지 체크를 수행할 수 있다. 
또한 자원의 처리가 끝난 후 응답내용에 대해서도 변경하는 처리를 할 수가 있다.
 
필터는 web.xml에 등록하는데 대표적으로 인코딩 변환, 로그인 여부확인, 권한체크, XSS방어 등의 요청에 대한 처리로 사용된다.

**Interceptor**

인터셉터는 스프링의 DistpatcherServlet이 컨트롤러를 호출하기 전, 후로 끼어들기 때문에 스프링 컨텍스트 내부에 존재하게된다. 그리고 스프링 내의 모든 객체(bean) 접근이 가능하다.
 
인터셉터는 여러 개를 사용할 수 있고 로그인 체크, 권한체크, 프로그램 실행시간 계산작업 로그확인, 업로드 파일처리등에 사용된다.

**AOP**

AOP는 Aspect Oriented Programming의 약자로 관점 지향 프로그래밍이라고 불린다. 
관점 지향은 쉽게 말해 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 각각 모듈화하겠다는 것이다. 
여기서 모듈화란 어떤 공통된 로직이나 기능을 하나의 단위로 묶는 것을 말한다. 


</div>
</details>

<details> 
  <summary>Spring Security란?</summary>
  <div markdown="1">
    
- Spring Security는 인증, 권한 관리 그리고 데이터 보호 기능을 포함하여 웹 개발 과정에서 필수적인 사용자 관리 기능을 구현하는데 도움을 주는 Spring의 강력한 프레임워크이다.
- Spring Security로 개발자들이 보안 관련 기능을 효율적이고 신속하게 구현할 수 있도록 도와준다.
  
  </div>
</details>

### JWT 기본 이해

<details>
 <summary> JWT란 무엇인가요?</summary>
  <div markdown="1">
    
  [인증인가 관련 내 블로그💛](https://velog.io/@serim1013/Spring%EC%9D%B8%EC%A6%9D%EA%B3%BC-%EC%9D%B8%EA%B0%80#jwt-%EA%B8%B0%EB%B0%98-%EC%9D%B8%EC%A6%9D)
    
  </div>
</details>

## 👩🏻‍💻코딩 시작!
<details>
 <summary> 회원가입 </summary>
  <div markdown="1">

**request**

![image](https://github.com/user-attachments/assets/5bb77975-aee0-4b7b-99bb-b26872a159f6)

**response**

![image](https://github.com/user-attachments/assets/2cde3391-520f-4f2e-93d7-b36539b2761b)

</div>
</details>

<details>
 <summary> 로그인 </summary>
  <div markdown="1">

**request**

![image](https://github.com/user-attachments/assets/046cc68b-fc8b-4e49-bd12-dad100731b2b)

**response**

![image](https://github.com/user-attachments/assets/18fba3f7-7d7e-4796-a97a-13af570ecb60)

</div> </details>

<details>
 <summary> 예외처리 </summary>
  <div markdown="1">
1. 회원가입 시 동일 유저일 경우

 ![image](https://github.com/user-attachments/assets/44ab6221-f9e9-4c43-a3a6-cc8c96ffbcf9)
  
2. 로그인 시 존재하는 username이 아닌 경우

![image](https://github.com/user-attachments/assets/1e2388c1-5641-47b1-b55e-add8af45fee1)

3. 로그인 시 비밀번호가 다를 경우

![image](https://github.com/user-attachments/assets/20f9cbbb-6597-4f6b-b21c-e275405dafb2)

</div> </details>

### Swagger UI
![image](https://github.com/user-attachments/assets/8e416366-1e78-4964-af24-fb4fe7509f71)
