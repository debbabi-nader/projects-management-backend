package io.isamm.projectsmanagement.enumerations;

import static io.isamm.projectsmanagement.enumerations.ProfileTypeEnum.ProfileTypeConstants.*;


public enum ProfileTypeEnum {

	ADMINISTRATOR(ADMINISTRATOR_PROFILE_TYPE),
	PROJECT_MANAGER(PROJECT_MANAGER_PROFILE_TYPE);
	
	private final String profileType;
	
	private ProfileTypeEnum(String profileType) {
		this.profileType = profileType;
	}
	
	public String getProfileType() {
		return this.profileType;
	}
	
	public static class ProfileTypeConstants {

		public static final String ADMINISTRATOR_PROFILE_TYPE = "ADMINISTRATOR";
		public static final String PROJECT_MANAGER_PROFILE_TYPE = "PROJECT_MANAGER";

	}

}
