/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnarlitronic.wa.tabtracker.dao;

import gnarlitronic.wa.tabtracker.dto.Tab;
import java.util.List;

/**
 *
 * @author Elnic
 */
public interface TabDao {

    public List<Tab> loadTabs();
    
    public Tab addTab(Tab tab);

    public List<Tab> searchTabs(String searchTerm, String category);

    public void deleteTab(String parameter);
    
}
