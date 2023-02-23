public class owo {
    static String build(int root_alicemod_orbit_angle_offset, int root_alicemod_orbit_distance_offset) {
        return new StringBuilder()
                .append("\t\t\tsolar_system = {\n")
                .append("\t\t\t\tspawn_planet = {\n")
                .append("\t\t\t\t\tclass = pc_asteroid\n")
                .append("\t\t\t\t\tlocation = root\n")
                .append("\t\t\t\t\torbit_location = yes\n")
                .append("\t\t\t\t\torbit_angle_offset = ")
                .append(root_alicemod_orbit_angle_offset)
                .append("\n")
                .append("\t\t\t\t\torbit_distance_offset = ")
                .append(root_alicemod_orbit_distance_offset)
                .append("\n")
                .append("\t\t\t\t\tsize = 2\n")
                .append("\t\t\t\t\thas_ring = no\n")
                .append("\n")
                .append("\t\t\t\t\tinit_effect = {\n")
                .append("\t\t\t\t\t\tset_name = \"ASTEROID\"\n")
                .append("\t\t\t\t\t\tset_planet_flag = new_asteroid\n")
                .append("\n")
                .append("\t\t\t\t\t\trandom_list = {\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_minerals_6 }\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_minerals_8 }\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_minerals_10 }\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_minerals_4 add_deposit = d_alloys_2 }\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_minerals_1 add_deposit = d_alloys_3 }\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_alloys_4 }\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_minerals_3 add_deposit = d_energy_3 }\n")
                .append("\t\t\t\t\t\t\t10 = { add_deposit = d_minerals_2 add_deposit = d_energy_5 }\n")
                .append("\t\t\t\t\t\t\t50 = {}\n")
                .append("\t\t\t\t\t\t}\n")
                .append("\t\t\t\t\t}\n")
                .append("\t\t\t\t}\n")
                .append("\t\t\t}").toString();
    }

    public static void main(String[] args) {
        int count = 7;
        double degrees = 360.0 / count;
        double degrees_current = 0;
        int distance = 2;
        for (int i = 0; i < count; i++) {
            distance++;
            degrees_current += degrees;

            StringBuilder s = new StringBuilder().append(i == 0 ? ("\t\tif = {\n") : ("\t\telse_if = {\n")).append("\t\t\tlimit = { check_variable = {\n").append("\t\t\t\t\twhich = alicemod_orbit_happened\n").append("\t\t\t\t\tvalue <= ").append(i + 1).append("\n").append("\t\t\t}\t}\n");
            s.append( build((int) Math.round(degrees_current), distance));
            s.append("\n\t\t}");

            System.out.println(s.toString());
        }
    }
}
