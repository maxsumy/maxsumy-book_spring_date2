package telran.java29.book.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.java29.book.model.Author;
import telran.java29.book.model.Book;
import telran.java29.book.model.Publisher;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Book> findByAuthorsName(String name) {
		TypedQuery<Author> query = em.createQuery("select a from Author a where a.name=?1", Author.class);
		query.setParameter(1, name);
		Author author = query.getSingleResult();
		List<Book> books = new ArrayList<Book>(author.getBooks());
		return books;
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String publisherName) {
		TypedQuery<Publisher> query = em.createQuery("select p from Publisher p where p.publisherName = ?1", Publisher.class);
		query.setParameter(1, publisherName);
		Publisher publisher = query.getSingleResult();
		return publisher.getBooks().stream();
	}

	@Override
	public boolean existsById(Long isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Optional<Book> findById(long isbn) {
		Book book = em.find(Book.class, isbn);
		return Optional.ofNullable(book);
	}

	@Override
	@Transactional
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	@Transactional
	public void delete(Book book) {
		em.remove(book);

	}

}
