package com.dugu.test.service.performance.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2022-07-23 10:48 下午
 */
@Getter
@Setter
@ToString
public class ScoreDistribution {

    private String score;
    private String score_distribution;
    private String leader_score;
    private String leader_score_distribution;
    private String total_score;
    private String total_score_distribution;
    private String distribution;
}
