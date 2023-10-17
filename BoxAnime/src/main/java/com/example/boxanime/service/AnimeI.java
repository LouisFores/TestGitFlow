package com.example.boxanime.service;

import com.example.boxanime.model.Anime;

import java.util.List;

public interface AnimeI {
    public Anime selectAnime(int id);
    public List<Anime> findAllAnime(String str);
    public List<Anime> selectAllAnime();
    public void insertAnime(Anime anime);
    public boolean updateAnime(Anime anime);
    public boolean deleteAnime(int id);

    public void addBox(Anime anime);
    public List<Anime> selectBoxAnime();

}
