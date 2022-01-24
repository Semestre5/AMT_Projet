import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static  org.mockito.Mockito.*;

class TestShopManagementServlet {
    @Test
    public void testMock(){
        List mockedList =mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}