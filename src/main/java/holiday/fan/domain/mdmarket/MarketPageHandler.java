package holiday.fan.domain.mdmarket;

/**
 * fan_board의 페이지 계산
 */
public class MarketPageHandler {

    private final int listSize = 10;
    private int totalPost; //총 글 갯수
    private int totalPage; //총 페이지 갯수 *
    private int offsetPost; //페이지 첫 글
    private int limitPost; //페이지 마지막 글
    private int viewPage; //현재 페이지
    private int beginPage; //목록 첫 페이지
    private int endPage; //목록 다음 페이지

    private boolean showNext = false;
    private boolean showPrev = false;

    public MarketPageHandler(int totalPost, int viewPage) {
        this.totalPost = totalPost;
        this.viewPage = viewPage;

        totalPage = totalPost/listSize +1;
        limitPost = viewPage*listSize < totalPost ? viewPage*listSize : totalPost;
        offsetPost = viewPage*10-9;
        beginPage = (viewPage/10*10)+1;
        endPage = totalPost*0.1 >= 1 ? totalPage : totalPost/10+1;

        showNext = totalPage != endPage;
        showPrev = beginPage != 1;


    }
}
