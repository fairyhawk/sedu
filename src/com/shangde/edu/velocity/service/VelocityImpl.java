package com.shangde.edu.velocity.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.velocity.domain.Velocity;

public class VelocityImpl extends BaseService implements IVelocity {

	public Integer addVelocity(Velocity velocity) {
		return this.simpleDao.createEntity("Velocity_NS.addVelocity", velocity);
	}

}
