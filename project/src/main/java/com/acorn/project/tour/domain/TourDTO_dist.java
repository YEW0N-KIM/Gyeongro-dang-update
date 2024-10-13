package com.acorn.project.tour.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO_dist {
    private Object title;
    private Object firstimage;
    private Object addr1;
    private Object addr2;
    private double dist;
    private Object contentid;
    private Object contenttypeid;
    private double mapx;
    private double mapy;
}
