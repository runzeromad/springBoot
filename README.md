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
    * resources/templates/layouts/header.mustache </br>
      ```html
      <!doctype html>
      <html lang="en">
         <head>
         <!-- Required meta tags -->
         <meta charset="utf-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
         (중략)
      ``` 
   * resources/templates/layouts/footer.mustache </br>
     ```html
        <!-- site info -->
        <div class="mb-5 container-fluid">
        <hr>
        <p>ⓒ CloudStudying | <a href="#">Privacy</a> | <a href="#">Terms</a></p>
        </div>
        (중략)
     ```       
   * resources/templates/greetings.mustache </br>
       ```html
       {{>layouts/header}}
       <!-- content -->
       <div class="bg-dark text-white p-5">
           <h1>{{username}}님, 반갑습니다!</h1>
       </div>
       {{>layouts/footer}}
       ```
   * resources/templates/goodbye.mustache </br>
       ```html
       {{>layouts/header}}
       <div class="bg-dark text-white p-5">
           <h1>{{username}}님, 다음에 또 만나요!</h1>
       </div>
       {{>layouts/footer}}
       ```
8. 셀프체크 </br>
    * resources/templates/quote.mustache </br>
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
   * resources/templates/articles/new.mustache </br>
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

                // 2. repository(레파지터리)로 entity(엔티티)를 저장
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
    * repository(리퍼지터리) : 엔티티가 DB 속 테이블에 저장 및 관리 될 수있게 하는 인터페이스  </br>
    * CrudRepository : JPA에서 제공하는 인터페이스로 이를 상속해 엔티티를 관리(CRUD) 할수 있다
    * repository/Articlerepository.java </br>
       ```java
       public interface ArticleRepository extends CrudRepository <Article, Long>{ // Article : 관리대상 엔티티의 클래스 타입, id : 관리대상 엔티티(Article.java)의 대표값 id 타입을 의미 즉 Long   

       }
       ```

7. 셀프체크 </br>
   * 뷰 : resources/templates/members/new.mustache </br>
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
    <img src="./src/main/resources/static/img/2023-11-22_day03_01.jpg" width="400px" alt="springBootProject"></img> </br></br>
>3. JPA(Java Persistence API)
>   * 자바 언어로 DB에 명령을 내리게 하는 도구(데이터를 객체 지향적으로 관리하도록 도와줌)
>   * Entity : 자바 객체를 DB가 이해할 수 있게 만든것(<b>이를 기반으로 테이블이 만들어짐</b>)  
>   * Repository : 엔티티가 DB속 테이블에 CRUD(저장 및 관리) 할수 있게 하는 인터 페이스</br></br>
>4. DTO를 DB에 저장하는 과정 
>   DTO를 엔티티로 변환 후 리파지터리를 이용해 엔티티를 DB에 저장</br>
    <img src="./src/main/resources/static/img/2023-11-22_day03_02.jpg" width="400px" alt="springBootProject"></img> </br></br>
>5. 의존성 주입
>   * 외부에서 만들어진 객체를 필요한 곳으로 가져오는 기법(스프링 부트는 @Autowired 어노테이션을 사용) </br></br>

----------------
### 4. JavaStudy Day 04 (lombok)
> Day 04 정리
> 1. 록봄(lombok) : 코드를 간소화해주는 라이브 러리 </br>
     <img src="./src/main/resources/static/img/2023-11-24_day04_01.jpg" width="400px" alt="springBootProject"></img> </br>
>   * @AllArgsConstructor : 클래스 안쪽의 모든 필드를 매개변수로 하는 생성자를 만드는 어노테이션으로, 이를 사용하면 클랫 내에 별도의 생성자를 만들지 않아도 된다 </br></br>
>   * @ToString : toStrong() 메서드를 사용하는 효과와 동일, 별도의 toStrong() 메서드를 사용하지 않아도 된다. </br></br>
>   * @Slf4j : 로깅기능 사용 log.info(); 형식으로 사용한다 </br>
----------------
### 5. JavaStudy Day 05 (Read)
1. 데이터 조회 과정 </br>
   <img src="./src/main/resources/static/img/2023-11-24_day04_02.jpg" width="500px" alt="springBootProject"></img>
   </br></br>
2. 컨트롤러에 URL요청 </br>
   <img src="./src/main/resources/static/img/2023-11-24_day04_03.jpg" width="500px" alt="springBootProject"></img>
   </br></br>
3. 리파지터리로 데이터 가져오기 </br>
   <img src="./src/main/resources/static/img/2023-11-24_day04_04.jpg" width="500px" alt="springBootProject"></img>
   </br></br>
4. 모델에 데이터 등록하기 </br>
   <img src="./src/main/resources/static/img/2023-11-24_day04_05.jpg" width="500px" alt="springBootProject"></img>
   </br></br>
5. 뷰 페이지 반환하기 </br>
   <img src="./src/main/resources/static/img/2023-11-24_day04_06.jpg" width="500px" alt="springBootProject"></img>
   </br></br>
6. 데이터 조회 실습으로 본 MVC, JPA, DB의 상호작용 </br>
   <img src="./src/main/resources/static/img/2023-11-24_day04_07.jpg" width="500px" alt="springBootProject"></img>
   </br></br> 
7. 데이터 목록 조회하기 </br>
   <img src="./src/main/resources/static/img/2023-11-24_day04_08.jpg" width="500px" alt="springBootProject"></img>
   </br></br>
8. 소스코드
   * controller/ArticlesController.java </br>
       
      ```java
     @Slf4j
     @Controller
     public class ArticleController {
          @Autowired
          private ArticleRepository articleRepository;

          @GetMapping("/articles/new")
          public String newArticleForm() {
          return "articles/new";
          }

          @PostMapping("/articles/create")
          public String createArticle(ArticleForm form) {
            log.info(form.toString());
            // 1. DTO를 엔티티로 변환
            Article article = form.toEntity();
            log.info(article.toString());

            // 2. 리파지터리로 엔티티를 DB에 저장
            Article saved = articleRepository.save(article);
            log.info(saved.toString());
            return "";
          }

          @GetMapping("/articles/{id}") // 조회 데이터 URL 맵핑
          // show 메서드
          public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아오기
              // @PathVariable : URL요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션
          
              // 1. id를 조회하여 데이터 가져오기
              Article articleEntity = articleRepository.findById(id).orElse(null);
              /*
              findById() : JPA의 CrudRepository가 제공하는 메서드로 특정 엔티티의 id값을 기준으로 데이터를 찾아 Optional 타입으로 반환
                           DB에 저장된 데이터를 id로 조회할때 findById()메서드를 사용
              orElse(null) : id값으로 데이터를 찾을 때 id값이 없을 경우 null을 반환                                        
              */

              // 2. 모델에 데이터 등록하기
              model.addAttribute("article", articleEntity);
              /*
              article이라는 이름으로 articleEntity 객체 추가
              model.addAttribute(String name , Object value); : 형태임
              */

              // 3. 뷰 페이지 반환하기
              return "articles/show";
          }

          @GetMapping("/articles")
          // index 메서드
          public String index(Model model) {
              // 1. 모든 데이터 가져오기
              List<Article> articleEntityList = articleRepository.findAll();
              /*
              findAll() : 리퍼지터리에서 모든 데이터를 가겨오는 메서드
                          이소스에서는 findAll() 메서드가 Iterable가 아닌 ArrayList를 반환하도록 articleRepository에서 findAll()을 Override하여 수정하였음
              */
     
              // 2. 모델에 데이터 등록하기
              model.addAttribute("articleList", articleEntityList); // 모델 객체 받아오기

              // 3. 뷰 페이지 반환
              return "articles/index";
          }
     }
      ```

   * entity/Article.java </br>

      ```java
     @AllArgsConstructor
     @NoArgsConstructor // 기본 생성자를 추가해주는 어노테이션
     @ToString
     @Entity
     public class Article {
        @Id
        @GeneratedValue
        private Long id;

        @Column
        private String title;

        @Column
        private String content;

     }
      ```   

   * repository/ArticleRepository.java
      ```java
      public interface ArticleRepository extends CrudRepository<Article, Long> {
        @Override
        ArrayList<Article> findAll(); // Iterable → ArrayList 수정
      }     
      ```
   * resources/templates/articles/show.mustache
      ```html
     {{>layouts/header}}
     
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Title</th>
                <th scope="col">Content</th>
            </tr>
            </thead>
            <tbody>
            {{#article}}
            <tr>
                <th>{{id}}</th>
                <td>{{title}}</td>
                <td>{{content}}</td>
            </tr>
            {{/article}}
            </tbody>
        </table>
        
        {{>layouts/footer}}   
      ```
   * resources/templates/articles/index.mustache
      ```html
        {{>layouts/header}}
        
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Title</th>
                <th scope="col">Content</th>
            </tr>
            </thead>
            <tbody>
            {{#articleList}}
            <tr>
                <th>{{id}}</th>
                <td>{{title}}</td>
                <td>{{content}}</td>
            </tr>
            {{/articleList}}
            </tbody>
        </table>
        
        {{>layouts/footer}}
      ```
9. 셀프체크 </br>
    * 컨트롤러 : controller/MemberController.java </br> 
    ````java
    // VIEW
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id : " + id); // id 확인

        // 1. id 조회해서 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("member", memberEntity);
        // 뷰페이지 반환
        return "members/show";
    }

    // LIST
    @GetMapping("/members")
    public String index(Model model){
        // 1. DB에서 모든 데이터 가져오기
        List<Member> memberEntityList = memberRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("memberList", memberEntityList);
        // 3. 사용자에게 보여줄 뷰 페이지 반환하기
        return "members/index";
    }
    ````

> Day 05 정리
> 1. 데이터 조회 과정 </br>
>    1. 사용자가 웹페이지에 데이터 조회 URL요청
>    2. 서버의 컨트롤러가 이 요청을 받아 해당 URL에서 찾으려는 데이터 정보(id)를 리파지터리에 전달
>    3. 리파지터리는 정보(id)를 가지고 DB에 데이터 조회를 요청
>    4. DB는 해당 데이터를 찾아 이를 엔티티로 반환
>    5. 반환된 엔티티는 모델을 통해 뷰 템플릿으로 전달
>    6. 최종 결과 뷰 페이지가 완성되 사용자 화면에 출력
     <img src="./src/main/resources/static/img/2023-11-24_day04_09.jpg" width="500px" alt="springBootProject"></img> </br>
     >   * @AllArgsConstructor : 클래스 안쪽의 모든 필드를 매개변수로 하는 생성자를 만드는 어노테이션으로, 이를 사용하면 클랫 내에 별도의 생성자를 만들지 않아도 된다 </br></br>
> 2. @PathVariable
>   * URL요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션 </br> </br>
> 3. findByid()
>   * JPA의 CrudRepository가 제공하는 메서드로, 특정 엔티티의 id 값을 기준으로 데이터를 찾아 Optional 타입으로 반환함 </br> </br>
> 4. findall()
>   * JPA의 CrudRepository가 제공하는 메서드로, 특정 엔티티를 모두 가져와 Iterable 타입으로 반환함 </br> </br>
> 5. {{#article}}{{/article}}
>   * 뷰페이지에서 모델에 등록된 article의 사용 범위를 지정할 때 사용하는 머스테치 문법
>   * {{#article}}부터 {{/article}}까지 범위 내에서 article 데이터를 사용할 수 있음 </br> </br>
> 6. 반환 데이터 타입 불일치 문제 해결법
>   * 특정 메서드가 반환하는 데이터 타입과 사용자가 작성한 반환 데이터 타입이 다를 경루 3가지 방법으로 해결가능
>    1. 메서드가 반환하는 데이터 타입을 사용자가 작성한 데이터 타입으로 캐스팅(형변환)하기
>    2. 사용자가 작성한 데이터 타입을 메서드가 반환하는 데이터 타입으로 수정하기
>    3. 메서드의 반환 데이터 타입을 원하는 타입으로 오버라이딩 하기
----------------     
### 6. 링크와 다이렉트 day 06
1. 셀프체크 </br>
    * 컨트롤러 : controller/MemberController.java </br>
      ````java
      @Slf4j
      @Controller
      public class MemberController {
           @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
           private MemberRepository memberRepository;  // memberRepository 객체 선언
           // Write
           @GetMapping("/members/new")
           public String newMembersForm(){
                return "members/new";
           }
   
           // Insert
           @PostMapping("/join") // post방식 데이터 전송 맵핑
           public String createMemberForm(MemberForm form){
               log.info(form.toString());
       
               Member member = form.toEntity();
               log.info(member.toString());
       
               Member saved = memberRepository.save(member);
               log.info(saved.toString());
       
               return "redirect:/members/" + saved.getId();
           }
   
           // VIEW
           @GetMapping("/members/{id}")
           public String show(@PathVariable Long id, Model model){
               log.info("id : " + id);
               // 1. id 조회해서 데이터 가져오기
               Member memberEntity = memberRepository.findById(id).orElse(null);
               // 2. 모델에 데이터 등록하기
               model.addAttribute("member", memberEntity);
               // 뷰페이지 반환
               return "members/show";
           }
   
           // LIST
           @GetMapping("/members")
           public String index(Model model){
               // 1. DB에서 모든 데이터 가져오기
               List<Member> memberEntityList = memberRepository.findAll();
               // 2. 모델에 데이터 등록하기
               model.addAttribute("memberList", memberEntityList);
               // 3. 사용자에게 보여줄 뷰 페이지 반환하기
               return "members/index";
           }
      }   
      ````
    * 엔티티 : /entity/Member.java </br>
      ````java
        @AllArgsConstructor // title와 content를 저장하는 생성자가 자동으로 생성됨 (lombok의 어노테이션)
        @NoArgsConstructor // 기본생성자 추가 어노테이션
        @ToString
        @Entity
        @Getter // 외부에서 객체의 데이터를 읽을 때 사용하는 어노테이션
        public class Member {
        @Id
        @GeneratedValue
        private Long id;
    
        @Column
        private String email;
    
        @Column
        private String password;
    
        }   
      ````
    * 리파지터리 : /repository/MemberRepository.java </br>
      ````java
      import java.util.ArrayList; // ArrayList import
    
      public interface MemberRepository extends CrudRepository <Member, Long>{  // CrudRepository 부모 -> ArticleRepository 자식 상속(extends)관계
      @Override
        ArrayList<Member> findAll(); // ArrayList를 override 해줌
      }
      ````
   *  view Templete : resources/templates/members/index.mustache </br>
      ```html
       {{>layouts/header}}
       <table class="table">
           <thead>
           <tr>
               <th scope="col">Id</th>
               <th scope="col">email</th>
               <th scope="col">password</th>
           </tr>
           </thead>
           <tbody>
           {{#memberList}}
           <tr>
               <th><a href="/members/{{id}}">{{id}}</a></th>
               <td>{{email}}</td>
               <td>{{password}}</td>
           </tr>
           {{/memberList}}
           </tbody>
       </table>
       <a href="/members/new">New members</a>
       {{>layouts/footer}}
      ```
   *  view Templete : resources/templates/members/index.mustache </br>
      ```html
      {{>layouts/header}}
      <table class="table">
          <thead>
          <tr>
              <th scope="col">Id</th>
              <th scope="col">email</th>
              <th scope="col">password</th>
          </tr>
          </thead>
          <tbody>
          {{#member}}
          <tr>
              <th>{{id}}</th>
              <td>{{email}}</td>
              <td>{{password}}</td>
          </tr>
          {{/member}}
          </tbody>
      </table>
      <a href="/members">GO to Member List</a>
      {{>layouts/footer}}      
      ```
> Day 06 정리
> 1. 링크
>   * HTML의 `<a>` 태그 혹은 `<form>` 태그로 작성
> 2. 리다이렉트
>   * 클라이언트가 보낸 요청을 마친 후 계속해서 처리할 다음 요청 주소를 재지시하는 것
----------------
### 7. JavaStudy Day 07 (update)
1. 데이터 수정 과정 </br>
   <img src="./src/main/resources/static/img/2023-11-27_day07_01.png" width="500px" alt="springBootProject"></img>
   <img src="./src/main/resources/static/img/2023-11-27_day07_02.png" width="500px" alt="springBootProject"></img>
   * 뷰(상세) 페이지 Edit 클릭 &rightarrow; 컨트롤러 해당 글의 id로 DB데이터를 찾아 가져옴</br> &rightarrow; 컨트롤러는 가져온 데이터를 
     뷰에서 사용할수 있도록 모델에 담음 &rightarrow; 모델에</br> 담겨진 데이터는 수정페이지에서 보여줌 </br> 
   * 폼데이터(수정한 데이터)를 DTO에 담아 컨트롤러에서 받음 &rightarrow; DTO를 엔티티로</br> 변환 &rightarrow; DB에서 데이터 갱신 &rightarrow;
     갱신된 데이터를 상세페이지로 리다이렉트 </br></br>

2. DB에 저장된 데이터를 가져와 수정 페이지에 출력하는 과정 요약
   <img src="./src/main/resources/static/img/2023-11-27_day07_03.png" width="500px" alt="springBootProject"></img></br></br>

3. 수정된 데이터 DB갱신 과정</br>
   <img src="./src/main/resources/static/img/2023-11-27_day07_04.png" width="500px" alt="springBootProject"></img></br></br>
4. 클라이언트-서버 간 데이터 처리를 위한 4가지 기술
   <img src="./src/main/resources/static/img/2023-11-27_day07_05.png" width="500px" alt="springBootProject"></img></br></br> 
5. CRUD를 위한 SQL 문과 HTTP 메서드

   | 데이터 관리          | SQL           | HTTP       |
   |-----------------|---------------|------------|
   | 데이터 생성 (Create) | INSERT        | POST       |
   | 데이터 조회 (Read)   | SELECT        | GET        |
   | 데이터 수정 (Update) | UPDATE(지정 컬럼) | PATCH |
   | 데이터 수정 (Update) | UPDATE(전체)    | PUT |
   | 데이터 삭제 (Delete) | DELETE        | DELETE     |

6. DB에 저장 된 기존 데이터가 갱신되는 과정</br>
   <img src="./src/main/resources/static/img/2023-11-27_day07_06.png" width="500px" alt="springBootProject"></img></br></br>
7. 상세 페이지로 리다이렉트하는 과정</br>
   <img src="./src/main/resources/static/img/2023-11-27_day07_07.png" width="500px" alt="springBootProject"></img></br></br>
9. 소스코드
    * controller/ArticlesController.java </br>
       ```java
       // EDIT VIEW
       @GetMapping("articles/{id}/edit") // URL 요청 접수
       public String edit(@PathVariable Long id, Model model){ // 모델 객체 받아오기
            // 1. 수정할 데이터 가져오기
            Article articleEntity = articleRepository.findById(id).orElse(null); // DB에서 수정할 데이터 가져오기 : 리퍼지터리에서 id로 DB테이블을 조회하여 데이터를 가져온 후 엔티티의 변수(articleEntity)에 담는다
            // 2. 모델에 데이터 담기
            model.addAttribute("article", articleEntity);
            // 3. 뷰 페이지 설정하기
            return "articles/edit";
       }      
      
       // UPDATE
       @PostMapping("articles/update") // show.mustache의 form데이터의 action 경로
       public String update(ArticleForm form){ // DTO받아오기(폼데이터)

            // log.info(form.toString()); // form데이터 확인
            Article articleEntity = form.toEntity(); // 폼데이터를 엔티티에 담아준다
            Article target = articleRepository.findById(articleEntity.getId()).orElse(null); // 엔티티의 ID로 리퍼지터리에서 데이터를 검색하여 target에 담아줌

            if(target != null){ // id가 존재할경우 데이터를 DB에 업데이트 해줌
                articleRepository.save(articleEntity);
            }

            return "redirect:/articles/" + articleEntity.getId(); // 데이터 업데이트 후 갱신되는 페이지 반환
       }      
       ```
    *  dto : dto/ArticleForm.java </br>
       ````java
        @AllArgsConstructor // title와 content를 저장하는 생성자가 자동으로 생성됨 (lombok의 어노테이션)
        @ToString // toString() 효과
        public class ArticleForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
            private Long id; // 키값
            private String title; // 제목받는 필드
            private String content; // 내용 받는 필드
        
            /*
            @AllArgsConstructor 대처됨
            // Constructor
            public ArticleForm(String title, String content) { // 전송받은 제목과 내용 저장 하는 생성자
            this.title = title;
            this.content = content;
            }
        
            @ToString 으로 대처됨
            // toString()
            @Override
            public String toString() { // 데이터를 잘 받았는지 확인할 메서드
            // 출력 결과 ArticleForm{title='제목', content='내용내용내용'}
            return "ArticleForm{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
            }
            */
        
            public Article toEntity() { // DTO객체를 엔티티로 반환
            return new Article(id, title, content);
            }
        }     
       ````

    *  view Templete : resources/templates/articles /show.mustache </br>
       ```html
       <a href="/articles/{{article.id}}/edit" class="btn btn-primary">Edit</a>
       ```      
    *  view Templete : resources/templates/articles /edit.mustache </br>
       ```html
        {{>layouts/header}}
 
        <form class="container" action="/articles/update" method="post">
            <input name="id" type="hidden" value="{{id}}">
            <div class="mb-3">
                <label class="form-label">제목</label>
                <input type="text" class="form-control" name="title" value="{{article.title}}">
            </div>
            <div class="mb-3">
                <label class="form-label">내용</label>
                <textarea class="form-control" rows="3" name="content">{{article.content}}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="/articles/{{article.id}}">Back</a>
        </form>
        
        {{>layouts/footer}}      
       ```    

10. 셀프체크 </br>
    * controller/MemberController.java </br>
      ``` java
      // EDIT VIEW
      @GetMapping("/members/{id}/edit")
      public String edit(@PathVariable Long id, Model model){
          Member memberEntity = memberRepository.findById(id).orElse(null);
          model.addAttribute("member", memberEntity);
  
          return "members/edit";
      }
  
      // UPDATE
      @PostMapping("/members/update")
      public String update(MemberForm form){

        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target != null){
            memberRepository.save(memberEntity);
        }

        return "redirect:/members/" + memberEntity.getId();
      }      
      ```
    *  dto : dto/MemberForm.java </br>
       ```` java
       @ToString // loombok로 대처
       @AllArgsConstructor // 클래스 안쪽의 모든 필드를 매개 변수로 하는 생성자를 만드는 어노테이션
           public class MemberForm { // DTO(data Transfer Object)폼데이터를 받아 담는 그릇
           private Long id;
           private String email;
           private String password;
       
           public Member toEntity() { // DTO객체를 엔티티로 반환
               return new Member(id, email, password);
           }
       }
       ```` 
    *  view Templete : resources/templates/members/show.mustache </br>
       ```html
       <a href="/members/{{member.id}}/edit" class="btn btn-primary">Edit</a>
       ```      
    *  view Templete : resources/templates/members/edit.mustache </br>
       ```html
        {{>layouts/header}}

         <form class="container" action="/members/update" method="post">
             <input name="id" type="hidden" value="{{id}}">
             <div class="mb-3">
                 <label class="form-label">이메일</label>
                 <input type="email" class="form-control" name="email" value="{{member.email}}">
             </div>
             <div class="mb-3">
                 <label class="form-label">비밀번호</label>
                 <input type="password" class="form-control" name="password" value="{{member.password}}">
             </div>
             <button type="submit" class="btn btn-primary">Submit</button>
             <a href="/members/{{member.id}}">Back</a>
         </form>
        
        {{>layouts/footer}}
       ```        
> Day 07 정리
> 1. 데이터 수정 1단계
>   * 수정페이지를 만들고 기존 데이터를 불러온다
      <img src="./src/main/resources/static/img/2023-11-27_day07_08.png" width="500px" alt="springBootProject"></img></br></br>
> 2. 데이터 수정 2단계
>   * 데이터를 수정해 DB에 반영한 후 결과를 볼 수 있게 상세 페이지로 리다이렉트 한다
      <img src="./src/main/resources/static/img/2023-11-27_day07_09.png" width="500px" alt="springBootProject"></img></br></br> 
### 8. JavaStudy Day 08 (delete)
1. 데이터 삭제과정 </br>
   <img src="./src/main/resources/static/img/2023-11-27_day08_01.png" width="500px" alt="springBootProject"></img></br>
   클라이언트가 삭제요청 &rightarrow; 삭제 요청 받은 컨트롤러는 리파지터리를 통애 Db에 저장된 데이터를 찾아 삭제 &rightarrow; 삭제 완료 시 결과 페이지로 리다이렉트
* RedirectAttributes : 리다이렉트시 결과값(삭제 완료 메시지)을 띄워주기 위해 사용하는 클래스 
* addFlashAttribute() : RedirectAttributes객체의 메서드로 리다이렉트된 페이지에서 사용할 일회성 데이터를 등록할수 있음</br>
* RedirectAttributes</br>
  <img src="./src/main/resources/static/img/2023-11-27_day08_02.png" width="500px" alt="springBootProject"></img></br></br>
2. 소스코드
    * controller/ArticlesController.java </br>
       ```java
       // DELETE
       @GetMapping("articles/{id}/delete")
       public String delete(@PathVariable Long id, RedirectAttributes rttr){ // id만 존재하면 된다
      
            Article target = articleRepository.findById(id).orElse(null);
      
            if(target != null){
                articleRepository.delete(target);
                rttr.addFlashAttribute("msg","삭제됐습니다.");
            };
    
            return "redirect:/articles";
       }
       ```

    *  view Templete : resources/templates/articles/show.mustache </br>
       ```html
       <a href="/articles/{{article.id}}/delete" class="btn btn-danger">Delete</a>
       ```      
   *  view Templete : resources/templates/layouts/header.mustache </br>
       ```html
       {{#msg}} <!--msg 시작부분-->
            <div class="alert alert-primary alert-dismissible">
       {{msg}} <!--메세지 출력 부분-->
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="close"></button>
            </div>
       {{/msg}} <!-- //msg 끝부분-->
       ```
3. 셀프 체크
    * controller/MembersController.java </br>
       ```java
       // DELETE
       @GetMapping("/members/{id}/delete")
       public String delete(@PathVariable Long id, RedirectAttributes rttr){
            Member target = memberRepository.findById(id).orElse(null);
            if(target != null){
                memberRepository.delete(target);
                rttr.addFlashAttribute("msg", "삭제됐습니다.");
            }
            return "redirect:/members";
       }
       ```
    *  view Templete : resources/templates/members/show.mustache </br>
       ```html
       <a href="/articles/{{member.id}}/delete" class="btn btn-danger">Delete</a>
       ```      

> Day 08 정리
> 1. 데이터 삭제 과정
>   * 클라이언트가 삭제 요청을 하면 서버는 DB에서 해당 데이터를 찾아 삭제하고, </br>
      클라이언트를 리다이렉트된 페이지로 보낸다 </br>
      <img src="./src/main/resources/static/img/2023-11-27_day08_01.png" width="500px" alt="springBootProject"></img></br></br>
> 2. RedirectAttributes
>   * 리다이렉트 페이지에서 사용할 일회성 데이터를 관리하는 객체로 이 객체의 </br>
      addFlashAttribute() 메서드로 리다이렉트된 페이지에서 사용할 일회성 데이터를 담을수 있다
      <img src="./src/main/resources/static/img/2023-11-27_day08_02.png" width="500px" alt="springBootProject"></img></br></br>

### 9. JavaStudy Day 09 (CRUD & SQL)
1. CRUD수행에 따른 DB의 SQL 쿼리 </br>
   <img src="./src/main/resources/static/img/2023-11-28_day09_01.png" width="500px" alt="springBootProject"></img></br></br>
2. JPA 로깅레벨

   | LEVEL | NAME  | 내용                                       |
   |-------|-------|------------------------------------------|
   | 1     | TRACE | DEBUG보다 더 상세한 정보                         |
   | 2     | DEBUG | 응용 프로그램을 디버깅하는 데 필요한 세부 정보(SQL 쿼리문 출력) |
   | 3     | INFO  | 응용 프로그램의 순조로운 진행 정보                      |
   | 4     | WARN  | 잠재적으로 유해한 상황 정보                          |
   | 5     | ERROR | 응용 프로그램이 수행할 수 있는 정도의 오류 정보              |
   | 6     | FATAL | 응용 프로그램이 중단될 만한 심각한 오류 정보                |
   | 7     | OFF   | 로깅기능 해제                                  |

   * 설정 파일 : resources\application.properties
     ````
     #JPA로깅 설정
     # 디버그 레벨로 SQL 쿼리 출력
     logging.level.org.hibernate.SQL=DEBUG
     
     # 쿼리 줄바꿈하기
     spring.jpa.properties.hibernate.format_sql=true
     
     # 쿼리의 매개 변수 값 추가
     logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
     
     # H2 DB URL설정
     spring.datasource.generate-unique-name=false
     # H2 고정 URL 설정
     spring.datasource.url=jdbc:h2:mem:testdb
     ````
3. id 자동생성
   * 엔티티 : /entity/Article.java </br>
     `````
     @id
     @GeneratedValue(strategy = GenerationType.IDENTITY) // id를 순차적으로 생성해줌
     @private Long id;
     `````
4. 셀프 체크
    * SQL 쿼리 </br>
       ```sql
       INSERT INTO burgers 
            (id, name, price, gram, kcal, protein)
       VALUES 
            ( 1,'행운버거 골드','1100','222','540','25'),
            ( 2,'트리플 치즈버거','4400','219','619','36'),
            ( 3,'빅맥','7700','223','583','27');
       ```
> Day 09 정리
> 1. JPA 로깅설정 ( resources\application.properties )
>    ````
>    # JPA로깅 설정
>    # 디버그 레벨로 SQL 쿼리 출력
>      logging.level.org.hibernate.SQL=DEBUG
>    # 쿼리 줄바꿈하기
>      spring.jpa.properties.hibernate.format_sql=true
>    # 쿼리의 매개 변수 값 추가
>      logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
>    ````  
> </br>2. 기본키와 ID 자동생성
>   * primary key : 기본키라고 불리우며 각각의 데이터를 유일하게 구분할 수 있도록 지정한 속성(id를 가장 많이 씀)
>   * entity/Article.java </br> 
>     @GeneratedValue(strategy = GenerationType.IDENTITY) // id를 순차적으로 생성해줌 </br></br>
> 3. CURD와 SQL </br>
>   <img src="./src/main/resources/static/img/2023-11-28_day09_01.png" width="500px" alt="springBootProject"></img></br>
>   * CREATE TABLE : 테이블 만드는 쿼리 
>   * INSERT : 테이블에서 데이터를 생성하는 쿼리
>   * SELECT : 테이블에서 데이터를 조회 하는 쿼리
>   * UPDATE : 테이블에서 데이터를 수정하는 쿼리
>   * DELETE : 테이블에세 데이터를 삭제하는 쿼리

### 10. REST API & JSON
1. REST API </br>
   * 서버 자원을 클라이언트에 구애받지 않고 사용할 수 있게 하는 설계 방식
   * HTTP 요청에 대한 응답으로 서버자원을 반환
   * 서버에 보내는 응답이 특정 기기에 종속 되지 않도록 모들 기기에 통용 될 수 있는 데이터 반환
     <img src="./src/main/resources/static/img/2023-11-29_day10_01.png" width="500px" alt="springBootProject"></img></br></br>

2. JSON 데이터
   * 키(key)와 값(value)으로 구성된 정렬되지 않은 속성(property)의 집합
   * 키(key)는 문자열 이므로 항상 큰따옴표(" ")로 감쌈
   * 값(value)는 문자열인 경우에만 큰따옴표(" ")로 감쌈
     ``` json
     * 형식
     {
        "key1" : "value",
        "key2" : "value",
        "key3" : "value"       
     }

     * sample     
     {
        "name" : "진돌이",
        "breeds" : "진돗개",
        "age" : 3
     }
     
     ```
3. REST API 사용 예 </br>
   <img src="./src/main/resources/static/img/2023-11-29_day10_02.png" width="500px" alt="springBootProject"></img>
    * GET : 데이터 조회 
    * PUT : 기존 데이터를 전부 새 내용으로 변경, 기존 데이터가 없다면 새로 생성
    * PATCH : 기존 데이터 중 일부만 새 내용으로 변경
    * DELETE : 데이터 삭제 </br></br>

5. HTTP 상태 코드

   | 상태코드                      | 설명                                 |
   |-------------------------|------------------------------------|
   | 1XX (정보)               | 요청이 수신돼 처리 중                       |
   | 2XX (성공)               | 요청이 정상적으로 처리                       |
   | 3XX (리다이렉션 메세지)    | 요청을 완료하려면 추가 행동이 필요                |
   | 4XX (클라이언트 요청 오류) | 클라이언트의 요청이 잘못돼 서버가 요청을 수행할 수 없습니다. | 
   | 5XX (서버 응답 오류)      | 서버 내부에서 에러가 발생해 클라이언트 요청을 수행하지 못함 |

6. HTTP 메시지
   * start line (시작라인) : HTTP 요청 또는 응답 내용이 있습니다. 시작 라인은 한줄로 끝남
   * headers(헤더) : HTTP 전송에 필요한 부가정보(metadata)가 있습니다.
   * blank line(빈 라인) : 헤더의 끝을 알리는 빈줄로, 헤더가 모두 전송되었음을 알림.
   * body(본문) : 실제 전송하는 데이터가 있음. </br></br>
     * HTTP Request Message </br> 
       <img src="./src/main/resources/static/img/2023-11-29_day10_03.png" width="500px" alt="springBootProject"></img>
     * HTTP Response Message </br>
       <img src="./src/main/resources/static/img/2023-11-29_day10_04.png" width="500px" alt="springBootProject"></img> 