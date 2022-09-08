package com.blogapp.post;

import com.blogapp.post.domain.Post;
import com.blogapp.post.services.IPostService;
import com.blogapp.user.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@DisplayName("PostController tests")
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IPostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setup() {

        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
//        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    // POST ALL

    @Test
    @DisplayName("Should return all posts")
    void getPostsShouldReturnAllPosts() throws Exception {
        User user1 = new User(10L, "Alice-1", "Last-1", "Alice", "",  "Alice1@example.com");
        User user2 = new User(20L, "Alice-2", "Last-2", "Alice", "",  "Alice2@example.com");

        List<Post> posts = List.of(
                new Post(1L, "TITLE-1", "BODY-1", LocalDateTime.now(), LocalDateTime.now(), user1 ),
                new Post(2L, "TITLE-2", "BODY-2", LocalDateTime.now(), LocalDateTime.now(), user2)
        );

        given(postService.getPosts()).willReturn(new ResponseEntity<>(posts, HttpStatus.OK));

        MockHttpServletResponse response = mockMvc.perform(
                        get("/posts")
                            .accept(MediaType.APPLICATION_JSON))
                            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    // POST BY POST ID

    // POST BY USER ID

    // POST BY USERNAME

    // PHOTO BY POST ID

    // ADD POST

    // TEST UPDATE POST

    // TEST DELETE POST

}