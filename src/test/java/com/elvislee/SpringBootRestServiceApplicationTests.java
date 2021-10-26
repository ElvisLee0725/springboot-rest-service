package com.elvislee;

import com.elvislee.controller.Library;
import com.elvislee.controller.LibraryController;
import com.elvislee.repository.LibraryRepository;
import com.elvislee.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringBootRestServiceApplicationTests {
	@Autowired
	LibraryController libraryController;

	@MockBean
	LibraryRepository libraryRepository;

	@MockBean
	LibraryService libraryService;

	@Test
	void contextLoads() {
	}

	@Test
	void givenISBNStartsWithZ_returnOLD() {
		String id = libraryService.buildId("ZMAN", 23);
		assertEquals("OLDZMAN23", id);

		String id1= libraryService.buildId("MAN", 25);
		assertEquals("MAN25", id1);
	}

	@Test
	void testAddBooks() {
		// mock
		Library lib = buildLibrary();
		// Mock the return value when dependent class is called:
		when(libraryService.buildId(lib.getIsbn(), lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.checkBookAlreadyExist(lib.getId())).thenReturn(true);

		ResponseEntity response = libraryController.addBookImplementation(lib);
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
	}

	public Library buildLibrary() {
		Library lib = new Library();
		lib.setAisle(123);
		lib.setBook_name("Spring");
		lib.setAuthor("Elvis Lee");
		lib.setIsbn("asdf");
		lib.setId("1234qaz");
		return lib;
	}

}
