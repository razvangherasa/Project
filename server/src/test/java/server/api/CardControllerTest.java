package server.api;

import commons.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commons.Card;
import commons.CList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;



public class CardControllerTest {
    private TestCardRepository cardRepository;
    private CardController cardController;
    Board board;
    CList list;

    @BeforeEach
    public void setup() {
        TestBoardRepository boardRepository = new TestBoardRepository();
        TestCListRepository cListRepository = new TestCListRepository();
        cardRepository = new TestCardRepository();

        CListController cListController = new CListController(cListRepository, boardRepository);
        cardController = new CardController(cardRepository, cListRepository);

        board = new Board();
        board.ID = 1;
        boardRepository.boards.add(board);

        list = new CList("list");
        list.ID = 1;
        cListRepository.lists.add(list);

        cListController.add(board.ID, list.title);
    }

    @Test
    public void addInvalidId() {
        var actual = cardController.addCard(-1,"title");
        assertEquals(BAD_REQUEST, actual.getStatusCode());

    }

    @Test
    public void addNullTitle() {
        Card card = new Card(null);
        var actual = cardController.addCard(list.ID, card.title);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addEmptyTitle(){
        Card card = new Card("");
        var actual = cardController.addCard(list.ID, card.title);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addValid(){
        Card card = new Card("cardTitle");
        var actual = cardController.addCard(list.ID, card.title);
        assertEquals("cardTitle", actual.getBody().title);
    }

    @Test
    public void addDatabaseIsUsed(){
        Card card = new Card("cardTitle");
        cardController.addCard(list.ID, card.title);
        assertTrue(cardRepository.calledMethods.contains("save"));
    }

    @Test
    public void getCardInvalidId(){
        Card card1 = new Card("title");
        cardController.addCard(list.ID, card1.title);
        var actual = cardController.getCard(card1.ID + 1);
        assertEquals(BAD_REQUEST, actual.getStatusCode());

    }

    @Test
    public void getCardTest(){
        var card1 = cardController.addCard(list.ID, "title1");

        long ID = card1.getBody().ID;
        assertEquals(ID, cardController.getCard(ID).getBody().ID);
    }
}

