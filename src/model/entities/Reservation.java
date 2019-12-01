package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date CheckIn;
	private Date checkOut;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {

	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut)  {
		if (!checkOut.after(checkIn)) {
			throw new DomainException( "Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.CheckIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return CheckIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - CheckIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

	}

	public void updateDates(Date checkIn, Date checkOut)  {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException( "Reservation dates for updates must be future dates");
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException( "Check-out date must be after check-in date");
		}

		this.CheckIn = checkIn;
		this.checkOut = checkOut;
		

	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + sdf.format(CheckIn) + ", check-out: " + sdf.format(checkOut)
				+ ", " + duration() + " nights";
	}

}
