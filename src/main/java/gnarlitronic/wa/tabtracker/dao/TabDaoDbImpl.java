/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnarlitronic.wa.tabtracker.dao;

import gnarlitronic.wa.tabtracker.dto.Tab;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Elnic
 */
public class TabDaoDbImpl implements TabDao {

    private JdbcTemplate jdbc;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }
    
    private static final String SQL_GET_ALL_TABS = "select * from TabList";
    private static final String SQL_ADD_TAB = "insert into TabList (SongName, Artist, URL) values (?,?,?)";
    private static final String SQL_SEARCH_SONG_NAMES = "select * from TabList where SongName like ?";
    private static final String SQL_SEARCH_ARTIST = "select * from TabList where Artist like ?";
    private static final String SQL_DELETE_TAB = "delete from TabList where Id = ?";
    
    @Override
    public List<Tab> loadTabs() {
        
        return jdbc.query(SQL_GET_ALL_TABS, new TabMapper());
    
    }
    
    
    //Adds row to TabList table using parameters stored in tab object
    @Override
    public Tab addTab(Tab tab) {
        jdbc.update(SQL_ADD_TAB, tab.getSongName(), tab.getArtist(), tab.getUrl());
        int tabId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        tab.setId(tabId);
        return tab;
    }

    //Retrieves rows from TabList table that match search input
    @Override
    public List<Tab> searchTabs(String searchTerm, String category) {
        switch (category) {
            case "artist": return jdbc.query(SQL_SEARCH_ARTIST, new TabMapper(), "%"+searchTerm+"%");
            
            case "songName": return jdbc.query(SQL_SEARCH_SONG_NAMES, new TabMapper(), "%"+searchTerm+"%");
            
            default: return jdbc.query(SQL_GET_ALL_TABS, new TabMapper());
        }
    }

    //Removes row from TabList table that matches id
    @Override
    public void deleteTab(String parameter) {
        jdbc.update(SQL_DELETE_TAB, parameter);
    }
    
    //This constructs a tab object from row in TabList table
    private static final class TabMapper implements RowMapper<Tab> {
        
        @Override
        public Tab mapRow(ResultSet rs, int i) throws SQLException {
            Tab tab = new Tab();
            tab.setId(rs.getInt("Id"));
            tab.setSongName(rs.getString("SongName"));
            tab.setArtist(rs.getString("Artist"));
            tab.setUrl(rs.getString("URL"));
            return tab;
        }
        
    }
    
}
