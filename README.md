# chanMarket
- HTTP 요청 처리는 MVC 모델을 기본으로 Repository와 request를 겸한 아키텍쳐입니다.
- 이미지 업로드는 Multipart form data를 사용해 처리하였습니다.



개발 언어
---------
- Java 11

</br>

개발 환경
---------
- Java: JDK 11.0.12  
- IDE: IntelliJ IDEA 2020.3.3 x64  
- Build Management: Gradle  
- Framework: SpringBoot  
 - ORM: Spring Data JPA   
- DBMS: Mysql


</br>





폴더 구조
---------
```
main
java
com.example.market
│  │  
├─ config
│  └─ Configurations.java
│  └─ RequestListener.java
│  └─ SecurityConfig.java
│  └─ SocketConfig.java
│  └─ WebSocketSessionConfigurator.java
│  │  
├─ controller
│  └─ BasketController.java
│  └─ BoardController.java
│  └─ CommentController.java
│  └─ ExceptionController.java
│  └─ imageController.java
│  └─ LoginController.java
│  └─ LogOutController.java
│  └─ MemberController.java
│  └─ MessageController.java
│  │  
├─ entity
│  └─ basket
│     └─ Basket.java
│     └─ BasketItem.java
│  └─ board
│     └─ Board.java
│     └─ BoardEditor.java
│  └─ comment
│     └─ Comment.java
│     └─ CommentEditor.java
│  └─ member
│     └─ Member.java
│     └─ MemberEditor.java
│     └─ Role.java
│  └─ BaseEntity.java
│  └─ ChatRoom.java
│  └─ Message.java
│  │  
├─ exception
│  └─ BoardNotFound.java
│  └─ CommentNotFound.java
│  └─ MemberNotFound.java
│  └─ TopExcertion.java
│  │  
├─ oauth2
│  └─ CustomOAuth2UserService.java
│  └─ OAuthAttributes.java
│  └─ OAuthController.java
│  │  
├─ repository
│  └─ BaskItemRepository.java
│  └─ ChatRoomRepository.java
│  └─ JpaBasketItemRepository.java
│  └─ JpaBasketRepository.java
│  └─ JpaBoardRepository.java
│  └─ JpaCommentRepository.java
│  └─ JpaMemberRepository.java
│  └─ MessageRepository.java
│  │  
├─ request
│  └─ board
│     └─ BoardCreate.java
│     └─ BoardEdit.java
│  └─ comment
│     └─ CommentCreate.java
│     └─ CommentEdit.java
│  └─ member
│     └─ MemberCreate.java
│     └─ MemberEdit.java
│  │ 
├─ response
│  └─ ErrorResponse.java
│  │ 
├─ service
│  └─ basket
│     └─ BasketService.java
│     └─ BasketServiceImpl.java
│  └─ board
│     └─ BoardService.java
│     └─ BoardServiceImpl.java
│  └─ chatRoom
│     └─ ChatRoomService.java
│     └─ ChatRoomServiceImpl.java
│  └─ comment
│     └─ CommentService.java
│     └─ CommentServiceImpl.java
│  └─ member
│     └─ MemberService.java
│     └─ MemberServiceImpl.java
│  └─ message
│     └─ MessageService.java
│     └─ MessageServiceImpl.java
│  │ 
└─  MarketApplication
```


</br>


유저(Member) 관련 기능
---------

### Member

```java
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Member{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String pw;
    private String tel;

    @Builder
    public Member(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.role = Role.USER;
        this.pw = pw;
        this.tel = tel;
    }

    public MemberEditor.MemberEditorBuilder toEditor(){
        return MemberEditor.builder()
                .email(email)
                .name(name)
                .pw(pw)
                .tel(tel);
    }

    public void edit(MemberEditor memberEditor){
        email = memberEditor.getEmail();
        name = memberEditor.getName();
        pw = memberEditor.getPw();
        tel = memberEditor.getTel();

    }

    @OneToMany(mappedBy = "member", cascade=CascadeType.REMOVE)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade=CascadeType.REMOVE)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade=CascadeType.REMOVE)
    private Basket basket;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public Member update(String name) {
        this.name = name;
        this.pw = "googlePw";
        this.tel = "01011111111";
        return this;
    }

    public  String getRoleKey() {
        return this.role.getKey();
    }
}
```
  
- email : Unique 제약 조건 설정을 설정하였습니다.
- toEditor() 를 이용해 현재 Member가 가지고 있는 데이터를 유지 시킬수 있어 null값을 방지하였습니다.
- @Enumerated(EnumType.STRING) 을 사용해 알아보기 편하게 하였습니다.
  
  
### MemberCreate

```java
@Getter
@Setter
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class MemberCreate {

    @NotBlank(message = "memberEmail 없음")
    private String email;

    @NotBlank(message = "memberName 없음")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "memberPw 없음")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력")
    private String pw;

    @NotBlank(message = "memberTel 없음")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바은 휴대폰 번호를 입력")
    private String tel;

    @Builder
    public MemberCreate(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.role = Role.USER;
        this.pw = pw;
        this.tel = tel;
    }
}
```



### MemberEditor

```java
@Getter
public class MemberEditor {

    private String email;
    private String name;
    private Role role;
    private String pw;
    private String tel;

    @Builder
    public MemberEditor(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.role = Role.USER;
        this.pw = pw;
        this.tel = tel;
    }
}
```




### MemberEdit

```java
@Getter
@ToString
public class MemberEdit {
    @NotBlank(message = "memberEmail 없음")
    private String email;

    @NotBlank(message = "memberName 없음")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "memberPw 없음")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력")
    private String pw;

    @NotBlank(message = "memberTel 없음")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바은 휴대폰 번호를 입력")
    private String tel;

    @Builder
    public MemberEdit(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.role = Role.USER;
        this.pw = pw;
        this.tel = tel;
    }
}
```

- BoardEdit, BoardEditor, Board로 관심사를 분리하였습니다. 


### MemberServiceImpl

```java
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

    private final JpaMemberRepository jpaMemberRepository;
    private final BasketService basketService;


    public void join(MemberCreate memberCreate){
        Member member = Member.builder()
                .email(memberCreate.getEmail())
                .name(memberCreate.getName())
                .pw(memberCreate.getPw())
                .tel(memberCreate.getTel())
                .build();
        validateDuplicateMember(member);
        basketService.addBasket(member);
        jpaMemberRepository.save(member);
    }

    public void validateDuplicateMember(Member member){
        Member findMember = jpaMemberRepository.sessionFindMember(member.getEmail());
        if (findMember != null){
            throw new IllegalStateException("이미 존재하는 회원");
        }
    }

    public Member login(String email, String pw){
        Member member = jpaMemberRepository.findMember(email, pw);
        return member;
    }

    public Member findByEmail(String email){
        Member member = jpaMemberRepository.sessionFindMember(email);
        return member;
    }


    public void editMember(MemberEdit updateParam){
        Member findMember = jpaMemberRepository.sessionFindMember(updateParam.getEmail());
        MemberEditor.MemberEditorBuilder memberEditorBuilder = findMember.toEditor();
        MemberEditor memberEditor = memberEditorBuilder
                .email(updateParam.getEmail())
                .name(updateParam.getName())
                .pw(updateParam.getPw())
                .tel(updateParam.getTel())
                        .build();
        findMember.edit(memberEditor);
    }

    public Member findMemberById(Long id){
        return jpaMemberRepository.getById(id);
    }

    @Override
    public void deleteMember(Member member) {
        jpaMemberRepository.delete(jpaMemberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new));
    }


}
```

- 회원가입을 할 때 장바구니도 추가 하였습니다.


### MemberController

```java
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("join")
    public String join() {
        return "member/join";
    }

    @PostMapping("/join")
    public String joinId(@Valid MemberCreate memberCreate) {
        try {
            memberService.join(memberCreate);
            return "redirect:/chanMarket/joinSucceed";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error";
        }
    }

    @GetMapping("joinSucceed")
    public String joinSucceed() {
        return "member/joinSucceed";
    }

    @GetMapping("myInfo")
    public String myInfo(HttpSession session, Model model) {
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "member/myInfo";
    }

    @GetMapping("myInfo/edit")
    public String memberEditForm(Model model, HttpSession session) {
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "member/editForm";
    }

    @PutMapping("myInfo")
    public String editItem(MemberEdit memberEdit) {
        memberService.editMember(memberEdit);
        return "redirect:/chanMarket/myInfo";
    }

    @DeleteMapping("myInfo")
    public void deleteMember(HttpSession session){
        Member member = memberService.findByEmail((String) session.getAttribute("loginMember"));
        memberService.deleteMember(member);
        session.invalidate();
    }
}
```

- 내 정보는 세션값을 이용해 구현하였습니다.

</br>


게시물(Board) 관련 기능
---------

### Board

```java
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long countView;
    private String title;
    private String price;
    private String itemInformation;
    @Column(length = 20000)
    private String category;

    private String filename;
    private String filepath;


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    public void updateView(Long countView) {
        this.countView = countView;
    }

    @Builder
    public Board(Member member, Long countView, String title, String price, String itemInformation, String category, String filename, String filepath) {
        this.member = member;
        this.countView = countView;
        this.title = title;
        this.price = price;
        this.itemInformation = itemInformation;
        this.category = category;
        this.filename = filename;
        this.filepath = filepath;
    }

    public BoardEditor.BoardEditorBuilder toEditor(){
        return BoardEditor.builder()
                .title(title)
                .price(price)
                .itemInformation(itemInformation)
                .category(category);
    }

    public void edit(BoardEditor boardEditor){
        title = boardEditor.getTitle();
        price = boardEditor.getPrice();
        itemInformation = boardEditor.getItemInformation();
        category = boardEditor.getCategory();

    }
```

- Member와 비슷하게 toEditor() 를 이용해 현재 Board 가지고 있는 데이터를 유지 시킬수 있어 null값을 방지하였습니다.

### BoardServiceImpl

```java
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final JpaBoardRepository boardRepository;
    @Override
    public Board createBoard(BoardCreate boardCreate, Member member, MultipartFile file) throws Exception{
        File imageFile = new File("C:\\Users\\chan\\images");
        imageFile.mkdir();

        String projectPath = System.getProperty("user.home") + "\\images"; //저장할 경로 지정
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

                Board board = Board.builder()
                        .member(member)
                        .title(boardCreate.getTitle())
                        .price(boardCreate.getPrice())
                        .itemInformation(boardCreate.getItemInformation())
                        .category(boardCreate.getCategory())
                        .countView(0L)
                        .filename(fileName)
                        .filepath("C:\\Users\\chan\\images\\" + fileName)
                        .build();
        boardRepository.save(board);
        return board;
    }
    public Board findBoard(Long id){
        return boardRepository.getById(id);
    }

    public void deleteBoard(Long id){
        boardRepository.delete(boardRepository.findById(id).orElseThrow(BoardNotFound::new));
    }

    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public void updateView(Long boardId, Board board){
        Board updateBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFound::new);
        updateBoard.updateView(board.getCountView());
    }

    @Transactional
    public void editBoard(Long boardId ,BoardEdit updateParam){
        Board board = findBoard(boardId);
        BoardEditor.BoardEditorBuilder boardEditorBuilder = board.toEditor();
        BoardEditor boardEditor = boardEditorBuilder
                .title(updateParam.getTitle())
                .price(updateParam.getPrice())
                .itemInformation(updateParam.getItemInformation())
                .category(updateParam.getCategory())
                .build();
        board.edit(boardEditor);
    }

    public Page<Board> boardSearchList(String keyword, String category,Pageable pageable){
        return boardRepository.findByTitleContainingAndCategoryContaining(keyword, category, pageable);
    }

}
```

- 글을 등록할 때 MultipartFile을 이용해 이미지파일도 업로드되게 구현했습니다.
- mkdir()을 이용해 폴더를 만들고 UUID을 이용해 파일이름 중복을 방지했습니다.


### BoardController

```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    @GetMapping("")
    public String boardList(Model model, HttpSession session, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String keyword, String category){
        Page<Board> list = null;
        if (keyword == null && category == null){
            list = boardService.boardList(pageable);
        }else {
            list = boardService.boardSearchList(keyword, category, pageable);
        }
        model.addAttribute("boards", list);
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "board/boardList";
    }

    @GetMapping("/{boardId}")
    public String boardInfo(@PathVariable Long boardId, Model model, HttpSession session, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Comment> comments = commentService.commentList(boardId, pageable);

        Board board = boardService.findBoard(boardId);
        Long countView = board.getCountView() + 1L;
        board.setCountView(countView);
        boardService.updateView(board.getId(), board);

        model.addAttribute("commentPage", comments);
        model.addAttribute("board", board);
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "board/boardInfo";
    }

    @GetMapping("/add")
    public String addForm(HttpSession session, Model model){
        if (session.getAttribute("loginMember") == null){
            return "member/login";
        }
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "board/addForm";
    }

    @PostMapping("/add")
    public String createBoard(@Valid BoardCreate boardCreate, HttpSession session, MultipartFile file){
        try {
            Board createBoard = boardService.createBoard(boardCreate, memberService.findByEmail((String) session.getAttribute("loginMember")), file);
            return "redirect:/chanMarket/board/" + createBoard.getId();
        }catch (Exception e){
            return "error/error";
        }
    }

    @GetMapping("/{boardId}/edit")
    public String boardEditForm(@PathVariable Long boardId, Model model, HttpSession session) {
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        model.addAttribute("board", boardService.findBoard(boardId));
        return "board/editForm";
    }

    @PutMapping("/{boardId}/edit")
    public String editBoard(@PathVariable Long boardId, BoardEdit boardEdit) {
        boardService.editBoard(boardId, boardEdit);
        return "redirect:/chanMarket/board/" + boardId;
    }

    @DeleteMapping("/{boardId}/delete")
    public String boardDelete(@PathVariable Long boardId, HttpSession session) {

        String loginEmail = (String) session.getAttribute("loginMember");
        String boardEmail = boardService.findBoard(boardId).getMember().getEmail();
        if (sameMemberCheck(loginEmail, boardEmail)) {
            boardService.deleteBoard(boardId);
            return "board/boardList";
        }
        else{
                return "error/error";
            }
        }

    public boolean sameMemberCheck(String email1, String email2){
        if (email1.equals(email2)) {
            return true;
        }
        else
            return false;
    }
}
```

- Pageable을 이용해 글 리스트에 페이징처리를 하였습니다.
- Pageable을 이용해 글 정보의 댓글도 페이징처리를 하였습니다.


### JpaBoardRepository
```java
public interface JpaBoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingAndCategoryContaining(String keyword, String category, Pageable pageable);

}

```

- 글 제목과 카테고리를 통해 검색할 수 있도록 하였습니다.

</br>


로그인(Login) 관련 기능
---------

### LoginController
```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/login")
public class LoginController {

    private final MemberService memberService;

    @GetMapping("")
    public String login() {
        return "member/login";
    }

    @PostMapping("")
    public String loginId(String email, String pw, HttpServletRequest request){
        if (memberService.login(email, pw) != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("loginMember", email);

            return "redirect:/chanMarket/board";
        }
        else return "error/error";
    }
}
```

- 로그인에 성공하면 세션이 생성됩니다.

</br>


이미지(Image) 관련 기능
---------

### ImageController

```java
@RestController
@RequiredArgsConstructor
public class ImageController {
    private final BoardService boardService;

    @GetMapping(value="/image/{boardId}", produces= MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Long boardId)
            throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Board board = boardService.findBoard(boardId);
        String fileDir = board.getFilepath(); // 파일경로

        try{
            fis = new FileInputStream(fileDir);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }
}
```

- 이미지파일을 바이트 스트림으로 읽기 위한 FileInputStream 객체를 생성해줍니다.
- InputStream의 메서드인 read()를 이용해 바이트를 읽고 리턴해줍니다.
