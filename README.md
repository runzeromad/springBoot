springBoot
=
### 1. JavaStudy Day 01 (스프링부트 시작하기)

1. 자바설치(windows 버전별 설치가능 17 - LTS버전) :
      https://adoptium.net/temurin/releases/
   * java -version 콘솔에서 자바 버전 확인</br></br>
2. 스프링 부트 프로젝트 만들기 : https://start.spring.io/ </br>
   <img src="./src/main/resources/static/img/2023-11-20_day01_02.png" width="1000px" alt="springBootProject"></img>
   </br></br>
3. helloworld 출력하기</br>
   <img src="./src/main/resources/static/img/2023-11-20_day01.png" width="1000px" alt="springBootProject"></img>
   </br></br>
----------------

### 2. JavaStudy Day 02 (뷰 템플릿과 MVC패턴)
1. Model(모델) : 데이터 관리역활 </br>
2. View Template(뷰) : 화면 담당 (스프링 프로젝트에서 <b>Mustache 뷰 템플릿 엔진 사용</b>)</br>
   > 디렉토리 위치 : src> main> resource> templates (.mustache 파일이 위치)

3. Controller(컨트롤러) : 클라이언트의 요청을 서버에서 처리하는 역활 </br>
   > 디렉토리 위치 : src> main> java > project name package > controller </br> 
     (일반적인 컨트롤러 이름은 XxxController 라고 명명함)
   
4. Handlebars/Mustache 플러그인 설치 </br>
   <img src="./src/main/resources/static/img/2023-11-20_day01_03.png" width="1000px" alt="springBootProject"></img>
   </br></br>
5. Controller </br>
   * controller/FirstController.java </br>
       ```java
      @Controller  // 컨트롤러 선언(annotaion-어노테이션 일종의 메타데이터)
       public class FirstController {
           @GetMapping("hi") // URL 맵핑(요청, 접수)
           public String niceToMeetYou(Model model) { // 모델 객체 가져오기 / 메서트 수행
               model.addAttribute("username","jang"); // 모델변수 등록 "변수명","변수값"
               return "greetings"; // 뷰 템플릿 반환
           }
   
           @GetMapping("/bye")
           public String seeYouNext(Model model) {
               model.addAttribute("nickname","홍길동");
           return "goodbye";
           }   
   
       }
       ```
6. BootStrap
    * 페이지 헤더 / Navbar 소스 Copy: https://getbootstrap.com
    * contents </br>
      <img src="./src/main/resources/static/img/2023-11-20_day01_04.png" width="1000px" alt="springBootProject"></img>
      </br>
    * Navbar
      <img src="./src/main/resources/static/img/2023-11-20_day01_05.png" width="1000px" alt="springBootProject"></img>
      </br></br>

7. View Templete </br>
    * templates/layouts/header.mustache </br>
      ```html
      <!doctype html>
      <html lang="en">
         <head>
         <!-- Required meta tags -->
         <meta charset="utf-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
         (중략)
      ``` 
   * templates/layouts/footer.mustache </br>
     ```html
        <!-- site info -->
        <div class="mb-5 container-fluid">
        <hr>
        <p>ⓒ CloudStudying | <a href="#">Privacy</a> | <a href="#">Terms</a></p>
        </div>
        (중략)
     ```       
   * templates/greetings.mustache </br>
       ```html
       {{>layouts/header}}
       <!-- content -->
       <div class="bg-dark text-white p-5">
           <h1>{{username}}님, 반갑습니다!</h1>
       </div>
       {{>layouts/footer}}
       ```
   * templates/goodbye.mustache </br>
       ```html
       {{>layouts/header}}
       <div class="bg-dark text-white p-5">
           <h1>{{username}}님, 다음에 또 만나요!</h1>
       </div>
       {{>layouts/footer}}
       ```
8. 셀프체크 </br>
    * templates/quote.mustache </br>
      ```html
      {{>layouts/header}}
      <div class="bg-dark text-white p-5">
        <h1>{{randomQuote}}</h1>
      </div>
      {{>layouts/footer}}

      ```      
   * controller/SecondController.java </br>
       ```java
       @Controller
       public class SecondController {
            @GetMapping("/random-quote")
            public String randomQuote(Model model) {
                String[] quotes = {
                "행복은 습관이다. 그것을 몸에 지니라. " +
                        "-허버드-",
                "고개 숙이지 마십시오. 세상을 똑바로 정면으로 " +
                        "바라보십시오. -헬렌 켈러-",
                "고난의 시기에 동요하지 않는 것, 이것은 진정 " +
                        "칭찬받을 만한 뛰어난 인물의 증거다. -베토벤-",
                "당신이 할 수 있다고 믿든 할 수 없다고 믿든, 믿는 " +
                        "대로 될 것이다. -헨리 포드-",
                "작은 기회로부터 종종 위대한 업적이 시작된다. " +
                        "-데모스테네스-"
                };
                int randInt = (int) (Math.random() * quotes.length);
                model.addAttribute("randomQuote", quotes[randInt]);
                return "quote";
            }
      }
       ```

> Day 02 정리
>1. 뷰 템플릿 : 웹페이지를 하나의 틀로 만들고 여기에 변수를 삽입해서 서로 다른 페이지를 보여주는 기술 </br></br> 
>2. MVC 패턴
>   * Model : 데이터 관리
>   * View : 웹페이지를 화면에 보여줌
>   * Controller : 클라이언트의 요청을 서버에서 처리 </br></br>
>3. 뷰 템플릿 생성위치 
>   * src> main> resource> templates (.mustache 확장자 파일이 위치) </br></br>
>4. 컨트롤러 생성위치
>   * src> main> java > 기본 패키지 안에 컨트롤러 패키지를 만든 후 자바 클래스 파일을 생성(확장자는 .java) </br></br>
>5. 모델을 통해 변수를 등록하는 방법
>   * 모델은 컨트롤러의 메서드에서 매개변수로 받아옴
>   * model.<b><u>addAttribute</u></b>("변수명","변수값") : addAttribute 메서드 모델에서 변수 등록할때 사용 </br></br>
>6. 헤더-푸터 레이아웃
>   * header - 헤더 : 내비게이션 영역
>   * contents - 컨텐츠 : 핵심내용 영역
>   * footer - 푸터 : 사이트 정보등 </br></br>
>7. 부트스트랩
>   * 프론트엔드 개발을 빠르고 쉽게 할 수 있는 반응형 웹사이트의 프레임워크 </br></br>
>8. 레이아웃 템플릿 생성과 적용
>   * 레이아웃 템플릿을 삽입할 때는 원하는 위치에서 {{>파일경로/파일명}} 형식으로 작성 </br></br>

----------------
### 3. JavaStudy Day 03 (CRUD)
1. 폼 < form > 태그에 실어 보낸 데이터를 서버의 컨트롤러가 객체(DTO : Data Transfer Object)에 담음 </br></br>
2. 입력폼 만들기
   * templates/articles/new.mustache </br>
     ```html
     {{>layouts/header}}
     <form class="container" action="/articles/create" method="post">
         <div class="mb-3">
             <label class="form-label">제목</label>
             <input type="text" class="form-control" name="title">
         </div>
         <div class="mb-3">
             <label class="form-label">내용</label>
             <textarea class="form-control" rows="3" name="content"></textarea>
         </div>
        <button type="submit" class="btn btn-primary">Submit</button>
     </form>
     {{>layouts/footer}}
     ``` 
3. 컨트롤러 만들기
    * controller/ArticlesController.java </br>
       ```java
      @Controller
      public class ArticleController {
      
            @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
            private ArticleRepository articleRepository;  // articleRepository 객체 선언
      
            @GetMapping("/articles/new")
            public String newArticleForm(){
                return "articles/new";
            }

            @PostMapping("/articles/create") // post방식 데이터 전송 맵핑
            public String createArticlesForm(ArticleForm form){ // 메서드 생성 및 반환값 작성 / ArticleForm form : 폼데이터를 DTO로 받기
                System.out.println(form.toString()); // 폼데이터가 잘 담겼는지 출력하여 확인
                // 결과 : ArticleForm{title='제목', content='내용내용내용'}

                // 1. DTO를 entity(엔티티)로 변환
                Article article = form.toEntity(); // toEntity() from객체를 엔티티 객체로 변환
                System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 출력
                // 결과 : Article{id=null, title='제목', content='내용내용내용'}

                // 2. repository(레파지토리)로 entity(엔티티)를 저장
                Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
                System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 출력
                // 결과 Article{id=1, title='제목', content='내용내용내용'}

            return "";
            }
      
      }

       ```

4. DTO 만들기
   * DTO : 데이터를 전달하기 위한 객체
   * dto/Articles.java </br>
      ```java

     public class ArticleForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
        private String title; // 제목받는 필드
        private String content; // 내용 받는 필드

        // Constructor
        public ArticleForm(String title, String content) { // 전송받은 제목과 내용 저장 하는 생성자
             this.title = title;
            this.content = content;
        }

        // toString()
        @Override
        public String toString() { // 데이터를 잘 받았는지 확인할 메서드
            // 출력 결과 ArticleForm{title='제목', content='내용내용내용'}
            return "ArticleForm{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        public Article toEntity() { // DTO객체를 엔티티로 반환
             return new Article(null, title, content);
        }
     }     
     

      ```

5. Entity 만들기
    * entity(엔티티) : 실제 DB 테이블과 맵핑 되는 클래스 (자바 객체를 DB가 이해할 수 있게 만든 것으로, 이를 기반으로 테이블이 만들어짐) </br>
    * entity/Articles.java </br>
       ```java
      @Entity
      public class Article {
      @Id
      @GeneratedValue
      private Long id;

      @Column
      private String title;

      @Column
      private String content;

      // Article 생성자 추가
      public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
      }

      // toString() 메서드 추가
      @Override
      public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
        }
      }      

       ```
6. Repository 만들기
    * repository(레파지터리) : 엔티티가 DB 속 테이블에 저장 및 관리 될 수있게 하는 인터페이스  </br>
    * CrudRepository : JPA에서 제공하는 인터페이스로 이를 상속해 엔티티를 관리(CRUD) 할수 있다
    * repository/Articlerepository.java </br>
       ```java
       public interface ArticleRepository extends CrudRepository <Article, Long>{ // Article : 관리대상 엔티티의 클래스 타입, id : 관리대상 엔티티(Article.java)의 대표값 id 타입을 의미 즉 Long   

       }
       ```

7. 셀프체크 </br>
   * 뷰 : templates/members/new.mustache </br>
     ```html
      {{>layouts/header}}
      <form class="container" action="/join" method="post">
          <div class="mb-3">
              <label class="form-label">이메일</label>
              <input type="email" class="form-control" name="email">
          </div>
          <div class="mb-3">
              <label class="form-label">비밀번호</label>
              <input type="password" class="form-control" name="password">
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
          <a href="/members">back</a>
      </form>
      {{>layouts/footer}} 
     ```      
   * 컨트롤러 : controller/MemberController.java </br>
       ```java
         @Controller
         public class MemberController {
            @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
            private MemberRepository memberRepository;  // memberRepository 객체 선언
            @GetMapping("/members/new")
            public String newMembersForm(){
                return "members/new";
            }

            @PostMapping("/join") // post방식 데이터 전송경로 맵핑
            public String createMemberForm(MemberForm form){
                System.out.println(form.toString());
         
                Member member = form.toEntity();
                System.out.println(member.toString());
         
                Member saved = memberRepository.save(member);
                System.out.println(saved.toString());
     
                return "";
             }
         }
       ```

   * DTO : dto/MemberForm.java </br>
       ```java
        public class MemberForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
            private String email;
            private String password;

            // Constructor
            public MemberForm(String email, String password) { // 전송받은 제목과 내용 저장 하는 생성자
                this.email = email;
                this.password = password;
            }

            // toString()
            @Override
            public String toString() { // 데이터를 잘 받았는지 확인할 메서드

            return "MemberForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
            }

            public Member toEntity() { // DTO객체를 엔티티로 반환
                return new Member(null, email, password);
            }
        }

       ```

   * 엔티티 : entity/Member.java </br>
       ```java
          @Entity
          public class Member {
            @Id
            @GeneratedValue
            private Long id;

            @Column
            private String email;

            @Column
            private String password;

            // Article 생성자 추가
            public Member(Long id, String email, String password) {
                this.id = id;
                this.email = email;
                this.password = password;
            }

            // toString() 메서드 추가
            @Override
            public String toString() {
                return "Member{" +
                        "id=" + id +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        '}';
            }
          }     
       ```

   * 리퍼지토리 : repository/MemberRepository.java </br>
       ```java
          public interface MemberRepository extends CrudRepository <Member, Long>{

          }
       ```

> Day 03 정리
>1. DTO
>   * Data Transfer Object로 데이터를 전달하기 위한 객체
>   * 주로 View(클라이언트) 와 Controller(서버) 사이에서 사용
>   * 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스)
>   * <b>DTO는 데이터 전달만을 위한 객체가 핵심<b>이며 이외의 비지니스 로직은 굳이 들어갈 필요가 없음</br></br>
>2. 폼데이터를 DTO로 받는 과정 </br>
>   <img src="./src/main/resources/static/img/2023-11-22_day03_01.jpg" width="400px" alt="springBootProject"></img> </br></br>
>3. JPA(Java Persistence API)
>   * 자바 언어로 DB에 명령을 내리게 하는 도구(데이터를 객체 지향적으로 관리하도록 도와줌)
>   * Entity : 자바 객체를 DB가 이해할 수 있게 만든것(<b>이를 기반으로 테이블이 만들어짐</b>)  
>   * Repository : 엔티티가 DB속 테이블에 CRUD(저장 및 관리) 할수 있게 하는 인터 페이스</br></br>
>4. DTO를 DB에 저장하는 과정 
>  DTO를 엔티티로 변환 후 리파지터리를 이용해 엔티티를 DB에 저장</br>
>   <img src="./src/main/resources/static/img/2023-11-22_day03_02.jpg" width="400px" alt="springBootProject"></img> </br></br>
>5. 의존성 주입
>   * 외부에서 만들어진 객체를 필요한 곳으로 가져오는 기법(스프링 부트는 @Autowired 어노테이션을 사용) </br></br>

