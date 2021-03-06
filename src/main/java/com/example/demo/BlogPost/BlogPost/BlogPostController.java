package com.example.demo.BlogPost.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPostController {
	 @Autowired
	    private BlogPostRepository blogPostRepository;

	    private static List<BlogPost> posts = new ArrayList<>();

	    @GetMapping(value = "/")
	    public String index(BlogPost blogPost, Model model) {
	    	 posts.removeAll(posts);
	         // adds all of the posts that already exist in the database to the array
	         for (BlogPost post : blogPostRepository.findAll()) {
	             posts.add(post);
	         }
	         model.addAttribute("posts", posts);
	         return "blogpost/index";
	    }

	    private BlogPost blogPost;

	    @GetMapping(value = "/blogposts/new")
	    public String newBlog(BlogPost blogPost) {
	        return "blogpost/new";
	    }

	    @PostMapping(value = "/blogposts")
	    public String addNewBlogPost(BlogPost blogPost, Model model) {
	        blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));

	        // Add new blog posts as they're created to our posts list for indexing
	        //posts.add(blogPost);

	        // Add attributes to our model so we can show them to the user on the results
	        // page
	        model.addAttribute("title", blogPost.getTitle());
	        model.addAttribute("author", blogPost.getAuthor());
	        model.addAttribute("blogEntry", blogPost.getBlogEntry());
	        return "blogpost/result";
	    }
	 // Request Mapping is used when Path Variables are used
	    // allows us to pass data from view to controller
	    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.GET)
	    //spring takes whatever value is in {id} and passes it to our method params using @PathVariable
	    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
	        //findById() returns an Optional<T> tells us that this may give us a null return 
	        Optional<BlogPost> post = blogPostRepository.findById(id);
	        //allows us to check that there is actually data there and not null
	        if(post.isPresent()){
	            BlogPost actualPost = post.get();
	            model.addAttribute("blogPost", actualPost);
	        }
	        return "blogpost/edit";
	    }
	    @RequestMapping(value = "/blogposts/update/{id}")
	    public String updateExsitingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
	        Optional<BlogPost> post = blogPostRepository.findById(id);
	        if (post.isPresent()) {
	            BlogPost actualPost = post.get();
	            actualPost.setTitle(blogPost.getTitle());
	            actualPost.setAuthor(blogPost.getAuthor());
	            actualPost.setBlogEntry(blogPost.getBlogEntry());
	            // save() works for both creating new post and overwriting existing post
	            // if the primary key of the Entity we give it matches the primary key of a
	            // record already in the database, it will save over it instead of creating a
	            // new record
	            blogPostRepository.save(actualPost);
	            model.addAttribute("title", blogPost.getTitle());
	            model.addAttribute("author", blogPost.getAuthor());
	            model.addAttribute("blogEntry", blogPost.getBlogEntry());
	        }
	 
	        return "blogpost/result";
	       }
	    
	    @RequestMapping(value = "/blogposts/delete/{id}")
	    public String deletePostById(@PathVariable Long id, BlogPost blogPost) {
	        blogPostRepository.deleteById(id);
	 
	        return "blogpost/delete";
	    }


}
