package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.mreview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    /*@Query("SELECT m, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " + "left outer join Review r on r.movie = m group by m")*/

    /*@Query("select m, max(mi), avg(coalesce(r.grade,0)),  count(distinct r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " + //영화 이미지에 대한 것
            "left outer join Review r on r.movie = m group by m ")*/

    @Query("SELECT m, mi, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
            "LEFT OUTER JOIN MovieImage mi on mi.movie = m " +
            "LEFT OUTER JOIN Review r on r.movie = m group by m ")
    Page<Object[]> getListPage(Pageable pageable); //페이지 처리

    @Query("select m, mi ,avg(coalesce(r.grade,0)), count(r)" +
            " from Movie m left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m " +
            " where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(@Param("mno") Long mno);  //특정 영화 클릭 시 처리 (영화보기)

}
