
package com.example.boxanime.service;

import com.example.boxanime.model.Anime;

import java.util.List;

public interface AnimeUserI {
    public void addBox(Anime anime);
    public Anime selectBoxAnime(int id);
    public List<Anime> selectAllBoxAnime();
    public boolean deleteBoxAnime(int id);

}
