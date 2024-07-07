package com.example.alstarboard;

import com.example.alstarboard.domain.Board;
import com.example.alstarboard.domain.Image;
import com.example.alstarboard.domain.User;
import com.example.alstarboard.repository.ImageMapper;
import com.example.alstarboard.repository.UserMapper;
import com.example.alstarboard.repository.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AlstarboardApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private ImageMapper imageMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testInsertUser() {
		User user = User.builder()
				.userEmail("test@gmail.com")
				.nickName("test")
				.build();

		userMapper.insertUser(user);

		assertNotNull(user.getId());
	}

	@Test
	void testSelectByEmail() {
		User user = userMapper.selectByEmail("test@gmail.com");
		assertNotNull(user.getId());
		assert user.getId() == 2L;
	}

	@Test
	void testInsertBoard() {
		Image image = Image.builder()
				.path("resources/static/images/test.webp")
				.url("https://www.google.com")
				.build();

		User user = User.builder()
				.userEmail("test@gmail.com")
				.nickName("test")
				.build();

		Board board = Board.builder()
				.title("New resources for Flutter game development")
				.text("We’re seeing promising results from our early investment in games, including success stories from industry leaders like Etermax and Supercell, who are each leveraging Flutter’s power and flexibility to efficiently deliver delightful user experiences and expand their reach.")
				.image(image)
				.user(user)
				.build();

		boardMapper.insertBoard(board);

		assertNotNull(board.getId());
	}

	@Test
	void testSelectBoardByUserId() {
		List<Board> boards = boardMapper.selectBoardByUserId(2L);
		assertNotNull(boards);
		assert !boards.isEmpty();
//		assert board.getId() == 1L;
	}
	
	@Test
	void testInsertImage() {
		Image image = Image.builder()
				.path("resources/static/images/test.webp")
				.url("https://www.google.com")
				.build();

		
		imageMapper.insertImage(image);

		assertNotNull(image.getId());
	}

}
