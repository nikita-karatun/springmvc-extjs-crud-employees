package ru.testtask.vo;

public class Employee
{
	private int id;
	private String firstName;
	private String lastName;
	private String secondName;
	private int age;
	private String experience;
	private String description;

	public Employee(int id, String firstName, String lastName, String secondName, int age, String experience,
			String description)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.secondName = secondName;
		this.age = age;
		this.experience = experience;
		this.description = description;
	}

	public Employee()
	{

	}

	public static Employee composeFromStringArray(String[] array)
	{
		Employee employee = new Employee();
		employee.firstName = array[1];
		employee.lastName = array[2];
		employee.secondName = array[3];
		String ageString = array[4];
		if (ageString.isEmpty())
			employee.age = -1;
		else
			employee.age = Integer.parseInt(ageString);
		employee.experience = array[5];
		employee.description = array[6];

		return employee;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getSecondName()
	{
		return secondName;
	}

	public void setSecondName(String secondName)
	{
		this.secondName = secondName;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getExperience()
	{
		return experience;
	}

	public void setExperience(String experience)
	{
		this.experience = experience;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", secondName="
				+ secondName + ", age=" + age + ", experience=" + experience + ", description=" + description
				+ "]";
	}

	public enum AgeSign
	{
		GREATE_THEN, LESS_THEN, EQUALS
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((experience == null) ? 0 : experience.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (experience == null)
		{
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
			return false;
		if (firstName == null)
		{
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null)
		{
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (secondName == null)
		{
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}

}