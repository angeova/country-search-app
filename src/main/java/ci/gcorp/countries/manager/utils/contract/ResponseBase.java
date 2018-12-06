
/*
 * Created on 2018-10-29 ( Time 12:35:20 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.gcorp.countries.manager.utils.contract;

import lombok.*;

/**
 * Response Base
 * 
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseBase {

	protected Status	status = new Status();
	protected boolean	hasError;
}
