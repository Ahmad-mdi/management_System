package com.manage;

import com.manage.model.User;
import com.manage.model.dto.UserDto;
import com.manage.model.mapper.UserMapper;
import com.manage.repository.user.UserRepository;
import com.manage.service.user.UserServiceImpl;
import com.manage.utils.hashing.SecurityUtils;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ProjectApplicationTests {
	private UserRepository repository;
	private SecurityUtils securityUtils;
	private UserServiceImpl userService;

	@Before
	public void setUp() {
		repository = Mockito.mock(UserRepository.class);
		securityUtils = Mockito.mock(SecurityUtils.class);
	}

	@Test
	public void testAddUser() throws Exception {
		// Create a mock UserDto
		UserDto userDto = new UserDto();
		userDto.setUsername("testUser");
		userDto.setPassword("testPassword");

		// Mock the securityUtils encrypt method
		Mockito.when(securityUtils.encryptSHA1("testPassword")).thenReturn("encryptedPassword");

		// Mock the repository save method
		User userEntity = UserMapper.mapToEntity(userDto);
		Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(userEntity);

		// Call the method
		UserDto savedUserDto = userService.addUser(userDto);

		// Verify the encrypted password and mapping
		assertEquals("encryptedPassword", userDto.getPassword());
		assertEquals(userEntity, UserMapper.mapToEntity(userDto));

		// Verify the repository save method was called
		Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(User.class));

		// Verify the returned UserDto
		assertEquals(userEntity, UserMapper.mapToEntity(savedUserDto));
	}
}
