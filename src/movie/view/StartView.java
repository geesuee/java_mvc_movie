package movie.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import movie.controller.MovieController;
import movie.model.dto.Reservation;

public class StartView {
	
	static Logger logger = Logger.getLogger("movie.view.Log4j");
	
	public static void reservationCheck(String crud) {
		System.out.println("\n---------- �α� ���� ----------");
		if(crud == "�߰�") { //������ �߰�
			logger.info("info - ������ �߰�!");
		} else if (crud == "����") {
			logger.info("info - ������ ����!");
		} else if (crud == "����") {
			logger.info("info - ������ ����!");
		} else {
			logger.info("info - ������ ��ȸ");
		}
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MovieController controller = MovieController.getInstance();
		
		try {
			System.out.println("-------- �����ϰ� ���� �޴��� �������ּ���. --------");
			System.out.println("1) �˻� \t 2) �߰� \t 3) ���� \t 4) ���� \t 5) �� ���� �� ����");
			int input = Integer.parseInt(br.readLine());
			
			// �˻�
			if(input == 1) {
				System.out.println("---------------- 1. ������ �˻� ----------------");
				System.out.println("1) ��ü ����Ʈ �˻� \t 2) ���� �׸� �˻� \t 3) ���ε� <Theater, Movie> �˻�");
				int subInput = Integer.parseInt(br.readLine());

				// ��ü ����Ʈ �˻�
				if(subInput == 1) {
					System.out.println("\n*** 01-1. ��� Movie �˻� ***");
					controller.getMovieList();	

					System.out.println("\n*** 01-2. ��� Theater �˻� ***");
					controller.getTheaterList();	

					System.out.println("\n*** 01-3. ��� Reservation �˻� ***");
					controller.getReservationList();
				}

				// ���� �׸� �˻�
				else if(subInput == 2) {

					System.out.println("\n*** 01-1. ��ȭ �������� Movie ���� �˻� ***");

					System.out.println("��ȭ ������ �Է����ּ���.");
					String movieTitle = br.readLine();
					controller.getMovie(movieTitle);

					System.out.println("\n*** 01-2. �󿵰� ��ȣ�� Theater ���� �˻� ***");

					System.out.println("�󿵰� �̸��� �Է����ּ���.");
					String theaterName = br.readLine();
					controller.getTheater(theaterName);

					System.out.println("\n*** 01-3. ������ �̸����� Reservation ���� �˻� ***");

					System.out.println("������ �̸��� �Է����ּ���.");
					String name = br.readLine();
					controller.getReservation(name);
				}
				
				// ���ε� <Theater, Movie> �˻�
				else if(subInput == 3) {
					
					System.out.println("\n*** 01. ���ε� <Theater, Movie> �˻� ***");
					controller.getMappedData();
				}
			}		
		
//			--------------------------------------------------------------------------
			
			// �߰�
			else if(input == 2) {
				System.out.println("---------------- 2. ������ �߰� ----------------");
				
				// ���ο� ���� ���� �߰�
				System.out.println("\n*** 02. ������ ���� �߰� ***");
				System.out.println("<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
				
				// �ڵ����� ���� ��ȣ �ߺ����� �ʰ� �����Ͽ� �ν��Ͻ� ����
				Reservation newReservation = new Reservation("������", "���� ���̺�2", "2��");
				
				controller.insertReservation(newReservation);
				reservationCheck("�߰�");
				
				System.out.println("\n<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
				
				System.out.println("\n02-1. �ش� �󿵰����� ������ �ʴ� ��ȭ ���� �߰�");
				// �������� ���εǾ� ���� ���� ��ȭ �Ҵ��Ͽ� �ν��Ͻ� ����
				Reservation newReservation1 = new Reservation("������", "����", "2��");
				controller.insertReservation(newReservation1);
				
				System.out.println("\n02-2. ���� ��ȣ�� �ߺ��Ǵ� ���� �߰�");
				// �������� �ߺ��Ǵ� ���� ��ȣ �Ҵ��Ͽ� �ν��Ͻ� ����
				Reservation newReservation2 = new Reservation(1, "������", "���� ���̺�2", "2��");
				controller.insertReservation(newReservation2);
				
				System.out.println("\n02-3. �ܿ� �¼��� ���� �󿵰� ���� �߰�");
				// �������� �ܿ� �¼��� ���� �󿵰� �Ҵ��Ͽ� �ν��Ͻ� ����
				// 2�� ���� �������̺�2�� �ܿ� �¼� ����
				Reservation newReservation3 = new Reservation("������", "�𰡵�", "1��");
				controller.insertReservation(newReservation3);
				
				System.out.println("\n<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
				
				//������ ������ �Է��Ͽ� �߰� ����
				System.out.println("\n02-4. Ű����� ����");
				System.out.println("��Ŀ� ���� �ۼ��� �ּ���");
				System.out.println("�����ڸ�:��ȭ��:�󿵰���ȣ(_��)");
				Reservation newrev = controller.startReservation();
				controller.insertReservation(newrev);
				controller.getReservationList();
				reservationCheck("�߰�");
				
				System.out.println("\n<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
			}
			
//			--------------------------------------------------------------------------
			
			else if(input == 3) {
				System.out.println("---------------- 3. ������ ���� ----------------");
				
				// ������ ���� �̸����� �˻� �� ���� / ���� �� �˻� / ���� �� �˻�
				System.out.println("\n*** 03. ������ ���� �̸����� �˻� �� ����, ���� �� ������ �˻� ***");
				System.out.println("<< ���� �� ������ �˻� >>");
				controller.getReservation("�÷���");
				
				controller.updateReservation("�÷���", "���� ũ����", "5��");
				reservationCheck("����");
				
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("�÷���");
				
				System.out.println("\n03-1. ���εǾ� ���� ���� �󿵰�, ��ȭ �������� ����");
				controller.updateReservation("�÷���", "���� ũ����", "7��");
				
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("�÷���");
			}
			
//			--------------------------------------------------------------------------
			
			else if(input == 4) {
				System.out.println("---------------- 4. ������ ���� ----------------");
				
				// ������ ���� �̸����� �˻� �� ���� / ���� �� �˻� / ���� �� �˻�
				System.out.println("\n*** 04. ȫ�浿 ������ ���� �� ������ ������ �˻� ***");
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("ȫ�浿");
			
				controller.deleteReservation("ȫ�浿");
				reservationCheck("����");
				
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("ȫ�浿");
			}
			
//			--------------------------------------------------------------------------
			
			else if(input == 5) {
				System.out.println("---------------- �� ���� �� ���� ----------------");
				
				// ��ü �˻�
				System.out.println("\n*** 01-1. ��� Movie �˻� ***");
				controller.getMovieList();	

				System.out.println("\n*** 01-2. ��� Theater �˻� ***");
				controller.getTheaterList();	

				System.out.println("\n*** 01-3. ��� Reservation �˻� ***");
				controller.getReservationList();
				
				// ���� �׸� �˻�
				System.out.println("\n*** 01-4. ��ȭ �������� Movie ���� �˻� ***");
				controller.getMovie("���� ������");

				System.out.println("\n*** 01-5. �󿵰� �̸����� Theater ���� �˻� ***");
				controller.getTheater("10��");

				System.out.println("\n*** 01-6. ������ �̸����� Reservation ���� �˻� ***");
				controller.getReservation("���ھ�");
				
				System.out.println("\n*** 01-7. ���ε� <Theater, Movie> �˻� ***");
				controller.getMappedData();
				
				// -----------------------------------------------------------------------------
				
				// ���ο� ���� ���� �߰�
				System.out.println("\n*** 02. ������ ���� �߰� ***");
				System.out.println("<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
				
				// �ڵ����� ���� ��ȣ �ߺ����� �ʰ� �����Ͽ� �ν��Ͻ� ����
				Reservation newReservation = new Reservation("������", "���� ���̺�2", "2��");
				
				controller.insertReservation(newReservation);
				reservationCheck("�߰�");
				
				System.out.println("\n<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
				
				System.out.println("\n02-1. �ش� �󿵰����� ������ �ʴ� ��ȭ ���� �߰�");
				// �������� ���εǾ� ���� ���� ��ȭ �Ҵ��Ͽ� �ν��Ͻ� ����
				Reservation newReservation1 = new Reservation("������", "����", "2��");
				controller.insertReservation(newReservation1);
				
				System.out.println("\n02-2. ���� ��ȣ�� �ߺ��Ǵ� ���� �߰�");
				// �������� �ߺ��Ǵ� ���� ��ȣ �Ҵ��Ͽ� �ν��Ͻ� ����
				Reservation newReservation2 = new Reservation(1, "������", "���� ���̺�2", "2��");
				controller.insertReservation(newReservation2);
				
				System.out.println("\n02-3. �ܿ� �¼��� ���� �󿵰� ���� �߰�");
				// �������� �ܿ� �¼��� ���� �󿵰� �Ҵ��Ͽ� �ν��Ͻ� ����
				// 2�� ���� �������̺�2�� �ܿ� �¼� ����
				Reservation newReservation3 = new Reservation("������", "�𰡵�", "1��");
				controller.insertReservation(newReservation3);
				
				System.out.println("\n<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
				
				//������ ������ �Է��Ͽ� �߰� ����
				System.out.println("\n02-4. Ű����� ����");
				System.out.println("��Ŀ� ���� �ۼ��� �ּ���");
				System.out.println("�����ڸ�:��ȭ��:�󿵰���ȣ(_��)");
				Reservation newrev = controller.startReservation();
				controller.insertReservation(newrev);
				controller.getReservationList();
				reservationCheck("�߰�");
				
				System.out.println("\n<< �߰� �� ��� ������ �˻� >>");
				controller.getReservationList();
				
				// -----------------------------------------------------------------------------
				
				// ������ ���� �̸����� �˻� �� ���� / ���� �� �˻� / ���� �� �˻�
				System.out.println("\n*** 03. ������ ���� �̸����� �˻� �� ����, ���� �� ������ �˻� ***");
				System.out.println("<< ���� �� ������ �˻� >>");
				controller.getReservation("�÷���");
				
				controller.updateReservation("�÷���", "���� ũ����", "5��");
				reservationCheck("����");
				
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("�÷���");
				
				System.out.println("\n03-1. ���εǾ� ���� ���� �󿵰�, ��ȭ �������� ����");
				controller.updateReservation("�÷���", "���� ũ����", "7��");
				
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("�÷���");

				// -----------------------------------------------------------------------------
				
				// ������ ���� �̸����� �˻� �� ���� / ���� �� �˻� / ���� �� �˻�
				// ������ ���� �̸����� �˻� �� ���� / ���� �� �˻� / ���� �� �˻�
				System.out.println("\n*** 04. ȫ�浿 ������ ���� �� ������ ������ �˻� ***");
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("ȫ�浿");
			
				controller.deleteReservation("ȫ�浿");
				reservationCheck("����");
				
				System.out.println("\n<< ���� �� ������ �˻� >>");
				controller.getReservation("ȫ�浿");

			} 
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}